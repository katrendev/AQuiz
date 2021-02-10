package ru.apteka.screen.categorylist.domain

import io.reactivex.Single
import ru.apteka.screen.categorylist.model.data.CatalogInfoResponse

interface CategoryListRepository {
    fun getCatalogAllItems(forceUpdate: Boolean = false): Single<CatalogInfoResponse>
}