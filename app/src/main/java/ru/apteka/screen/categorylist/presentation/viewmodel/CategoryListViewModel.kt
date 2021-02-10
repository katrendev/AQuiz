package ru.apteka.screen.categorylist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import ru.apteka.base.*
import ru.apteka.screen.categorylist.domain.CategoryListInteractor
import ru.apteka.screen.categorylist.model.domain.CatalogCategory
import ru.apteka.screen.categorylist.model.domain.CatalogInfo
import ru.apteka.utils.extensions.applySingleSchedulers

class CategoryListViewModel(
    private val categoryListInteractor: CategoryListInteractor,
    private val categoryId: String? = null,
    val title: String? = null
) : BaseViewModel() {
    private val refreshSubject = PublishSubject.create<Unit>()

    val categoryClick = SingleLiveEvent<CatalogCategory>()
    val rootCategoryClick = SingleLiveEvent<CatalogCategory>()
    val categoryAdditionalClick = SingleLiveEvent<CatalogCategory>()
    val backClick = SingleLiveEvent<Unit>()
    val items = MutableLiveData<List<CategoryItemViewModel>>()
    val isProgress = MutableLiveData<Boolean>().apply { value = true }
    val isRefreshing = MutableLiveData<Boolean>()


    init {
        disposable += refreshSubject
            .startWith(Unit)
            .flatMapSingle {
                categoryListInteractor.getCategoryTree()
                    .applySingleSchedulers()
            }
            .subscribe({ catalogInfo ->
                handleCatalogInfo(catalogInfo)
            }, { error ->
                isError.value = true
                items.value = emptyList()
            })
    }

    private fun handleCatalogInfo(catalogInfo: CatalogInfo) {
        isRefreshing.postValue(false)
        isProgress.postValue(false)

        val map = when (categoryId) {
            null -> catalogInfo.category?.map { createCategoryVm(it) }
            else -> catalogInfo.category?.find { it.searchUrl == categoryId }?.subGroup?.map { createCategoryVm(it) }
        }
        map?.firstOrNull()?.isExpanded?.value = true
        items.value = map
        isError.value = false
    }

    private fun createCategoryVm(category: CatalogCategory): CategoryItemViewModel {
        return CategoryItemViewModel(category).apply {
            click.safeSubcribe(this@CategoryListViewModel) {
                when {
                    !category.subGroup.isNullOrEmpty() -> rootCategoryClick.postValue(category)
                    else -> categoryClick.postValue(category)
                }
            }
            category.subGroup?.map { createCategoryVm(it) }?.let { children.addAll(it) }
        }
    }

    fun refresh() {
        refreshSubject.onNext(Unit)
        isRefreshing.value = true
    }

    fun backClick() {
        backClick.call()
    }
}