package ru.apteka.base.commonapi.clients

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.apteka.base.commonapi.response.BannerInfoModelResponse

interface BannerClient {

    //Инфо о банере
    @GET("/Banner")
    fun getBanner(
        @Query("bannerSectionUrl") bannerSectionUrl: String,         //Ссылка на раздел банеров

        //Для потоварного банера обязательно нужно указать id ItemGroup или GoodVendor или GoodSet
        // Для поискового банера обязательно нужно указать поисковую фразу
        // Для банера корневой категории нужно указать url категории
        @Query("optionalParametr") optionalParametr: String? = null,

        @Query("count") count: Int? = null                            //Опицональный параметр, если не задано, то возвращает один рандомный активный банер из раздела
    ): Single<List<BannerInfoModelResponse>>
}