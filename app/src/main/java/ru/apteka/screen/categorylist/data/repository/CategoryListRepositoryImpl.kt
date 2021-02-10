package ru.apteka.screen.categorylist.data.repository

import io.reactivex.Single
import ru.apteka.base.commonapi.clients.AptekaRuApiClient
import ru.apteka.screen.categorylist.domain.CategoryListRepository
import ru.apteka.screen.categorylist.model.data.CatalogInfoResponse

class CategoryListRepositoryImpl(
    private val apiClient: AptekaRuApiClient
) : CategoryListRepository {

    var cache: CatalogInfoResponse? = null

    override fun getCatalogAllItems(forceUpdate: Boolean): Single<CatalogInfoResponse> {
        return when {
            forceUpdate || cache == null -> apiClient.catalogClient.getCatalogAllItems().doOnSuccess { cache = it }
            else -> Single.just(cache)
        }
    }
}