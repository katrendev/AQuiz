package ru.apteka.base.commonapi.factory

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ApiFactoryImpl(private val retrofit: Retrofit, private val okHttpClient: OkHttpClient) : ApiFactory {

    override fun <T> createClient(service: Class<T>): T {
        return newRetrofit.create(service)
    }

    private val newRetrofit: Retrofit by lazy { createRetrofit() }

    private fun createRetrofit(): Retrofit =
        retrofit
            .newBuilder()
            .client(okHttpClient)
            .build()
}