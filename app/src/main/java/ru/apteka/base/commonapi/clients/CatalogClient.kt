package ru.apteka.base.commonapi.clients

import io.reactivex.Single
import retrofit2.http.GET
import ru.apteka.screen.categorylist.model.data.CatalogInfoResponse

interface CatalogClient {

    //Возвращает два массива разделов каталога (по группам и по симптомам)
    @GET("/Catalog/AllItems")
    fun getCatalogAllItems(): Single<CatalogInfoResponse>
}