package ru.apteka.screen.aptekanew.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import ru.apteka.base.BaseViewModel
import ru.apteka.base.SingleLiveEvent
import ru.apteka.base.call
import ru.apteka.base.safeSubcribe
import ru.apteka.screen.aptekanew.domain.model.BannerInfoModel
import ru.apteka.screen.categorylist.domain.CategoryListInteractor
import ru.apteka.screen.categorylist.model.domain.CatalogCategory
import ru.apteka.screen.categorylist.model.domain.CatalogInfo
import ru.apteka.screen.main.domain.interactor.BannersInteractor
import ru.apteka.utils.extensions.applySingleSchedulers
import ru.apteka.utils.extensions.isNoContent
import ru.apteka.utils.putValue

class NewAptekaViewModel(
    private val bannersInteractor: BannersInteractor,
    private val categoryListInteractor: CategoryListInteractor
) : BaseViewModel() {
    companion object {
        const val BRING_A_FRIEND_BANNER_URL = "/services/loyalty/?utm_source=скидка_10_приведи_друга&utm_medium=banner&utm_campaign=программа_приведи_друга10/services/loyalty/?utm_source=%D1%81%D0%BA%D0%B8%D0%B4%D0%BA%D0%B0_10&utm_medium=banner&utm_campaign=%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B0_%D0%BF%D1%80%D0%B8%D0%B2%D0%B5%D0%B4%D0%B8_%D0%B4%D1%80%D1%83%D0%B3%D0%B0#bring-a-friend"
    }

    val aptekaSearchViewModel = AptekaSearchViewModel()

    private val refreshSubject = PublishSubject.create<Unit>()
    val isRefreshing = MutableLiveData<Boolean>().putValue(false)

    val items = MutableLiveData<List<BaseViewModel>>()
    val isProgress = MutableLiveData<Boolean>().apply { value = true }
    val openWebView = SingleLiveEvent<String>()
    val openMiniShop = SingleLiveEvent<String>()
    val openProductsForCategory = SingleLiveEvent<CatalogCategory>()
    val openCategoryAdditional = SingleLiveEvent<CatalogCategory>()
    val openCategoryList = SingleLiveEvent<CatalogCategory>()
    val openAllCategories = SingleLiveEvent<Unit>()
    val cartInviteFriendsBannerClickEvent = SingleLiveEvent<Unit>()

    val categoryCache = mutableListOf<CatalogCategory>()

    init {
        disposable += refreshSubject
            .startWith(Unit)
            .flatMapSingle {
                Singles.zip(
                    categoryListInteractor.getCategoryTree()
                        .applySingleSchedulers(),
                    bannersInteractor.getBanner(
                        bannerSectionUrl = "home_minishops",
                        count = 12
                    )
                        .applySingleSchedulers()
                        .onErrorReturn {
                            if (!it.isNoContent()) {
                                recordException(it)
                            }
                            emptyList()
                        }
                )
            }
            .subscribe({ (catalogInfo, bannersMinishop) ->
                handleCatalogInfo(catalogInfo, bannersMinishop)
            }, { error ->
                isError.postValue(true)
                items.postValue(emptyList())
            })

    }

    private fun handleCatalogInfo(catalogInfo: CatalogInfo, bannersMinishop: List<BannerInfoModel>) {
        isRefreshing.postValue(false)
        isProgress.postValue(false)

        categoryCache.clear()
        categoryCache.addAll(catalogInfo.category.orEmpty())

        val size = catalogInfo.category?.size ?: 0
        val categoryTiles = if (size > 6) {
            catalogInfo.category?.subList(0, 5)?.map { createCategoryVm(it) }
                ?.plus(CategoryTileAllItemViewModel().apply {
                    click.safeSubcribe(this@NewAptekaViewModel) {
                        openAllCategories.call()
                    }
                })
        } else {
            catalogInfo.category?.map { createCategoryVm(it) }
        }.orEmpty()

        val bannerMinishopTiles = bannersMinishop.mapIndexed { index, bannerInfoModel ->
            createBannerViewModel(
                banner = bannerInfoModel,
                isFirstColumn = index % 2 == 0
            )
        }

        val itemsToShow = mutableListOf<BaseViewModel>().apply {
            addAll(categoryTiles)
            if (bannerMinishopTiles.isNotEmpty()) {
                val specialTitle = TitleItemViewModel("Специальные предложения")
                add(specialTitle)
                addAll(bannerMinishopTiles)
            }
        }

        items.postValue(itemsToShow)
        isError.postValue(false)
    }

    private fun createBannerViewModel(banner: BannerInfoModel, isFirstColumn: Boolean = false): BannerTileItemViewModel {
        return BannerTileItemViewModel(banner, isFirstColumn).apply {
            click.observe(this@NewAptekaViewModel) {
                if (banner.url == BRING_A_FRIEND_BANNER_URL) {
                    cartInviteFriendsBannerClickEvent.call()
                    return@observe
                }

                val split = banner.url?.split("/")?.filter { it.isNotEmpty() }
                val startPath = split?.getOrNull(0)
                when (startPath) {
                    "services" -> openWebView.postValue(banner.url)
                    "mini-shop" -> openMiniShop.postValue(split.getOrNull(1))
                    "category" -> {
                        val categoryUrl = split.filterIndexed { index, _ -> index > 0 }.joinToString("/")
                        val rootCategory = categoryCache.firstOrNull { it.searchUrl == categoryUrl }
                        if (rootCategory != null) {
                            openCategory(rootCategory)
                        } else {
                            val category = categoryCache.flatMap {
                                it.subGroup.orEmpty()
                            }.firstOrNull {
                                it.searchUrl == categoryUrl
                            }
                            if (category == null) {
                                openWebView.postValue(banner.url)
                            } else {
                                openProductsForCategory.postValue(category)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun createCategoryVm(category: CatalogCategory): CategoryTileItemViewModel {
        return CategoryTileItemViewModel(category.name, category.imageUrl).apply {
            click.safeSubcribe(this@NewAptekaViewModel) {
                openCategory(category)
            }
        }
    }

    //use only for root categories (categoryAdditional exists only for root)
    private fun openCategory(category: CatalogCategory) {
        openCategoryList.postValue(category)
    }

    fun refresh() {
        refreshSubject.onNext(Unit)
        isRefreshing.postValue(true)
    }

    fun getSpanSize(position: Int): Int {
        val value = when (items.value?.get(position)) {
            is CategoryTileItemViewModel -> 2
            is CategoryTileAllItemViewModel -> 2
            is TitleItemViewModel -> 6
            is BannerTileItemViewModel -> 3
            else -> 6
        }
        return value
    }
}