package ru.apteka.dagger

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.apteka.BuildConfig
import ru.apteka.base.ResourceManager
import ru.apteka.base.commonapi.clients.AptekaRuApiClient
import ru.apteka.base.commonapi.clients.AptekaRuApiClientImpl
import ru.apteka.base.commonapi.clients.BannerClient
import ru.apteka.base.commonapi.clients.CatalogClient
import ru.apteka.base.commonapi.factory.ApiFactory
import ru.apteka.base.commonapi.factory.ApiFactoryImpl
import ru.apteka.base.commonapi.interceptors.BaseInterceptor
import ru.apteka.base.data.ISharedPreferenceManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.apteka.ru/appapi/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideBaseInterceptor(
        preferenceManager: ISharedPreferenceManager,
        resourceManager: ResourceManager
    ): BaseInterceptor =
        BaseInterceptor(
            preferenceManager,
            resourceManager
        )

    @Provides
    @Singleton
    fun provideOkHttp(
        baseInterceptor: BaseInterceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(baseInterceptor)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            httpClient.addNetworkInterceptor(StethoInterceptor())
        }
        httpClient.connectTimeout(40, TimeUnit.SECONDS)
        httpClient.readTimeout(40, TimeUnit.SECONDS)
        httpClient.writeTimeout(40, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideApiFactory(
        retrofit: Retrofit,
        okHttpClient: OkHttpClient
    ): ApiFactory =
        ApiFactoryImpl(retrofit, okHttpClient)

    @Provides
    fun provideCatalogClient(apiFactory: ApiFactory): CatalogClient {
        return apiFactory.createClient(CatalogClient::class.java)
    }

    @Provides
    fun provideBannerClient(apiFactory: ApiFactory): BannerClient {
        return apiFactory.createClient(BannerClient::class.java)
    }

    @Provides
    @Singleton
    fun provideApiClient(
        catalogClient: CatalogClient,
        bannerClient: BannerClient,
    ): AptekaRuApiClient = AptekaRuApiClientImpl(
        catalogClient,
        bannerClient,
    )
}