package ru.apteka.base.commonapi.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.apteka.R
import ru.apteka.base.ResourceManager
import ru.apteka.base.data.ISharedPreferenceManager

class BaseInterceptor(
    private val preferenceManager: ISharedPreferenceManager,
    private val resourceManager: ResourceManager
) : Interceptor {

    private val versionWithStamp = resourceManager.getString(R.string.version_number_with_stamp)

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .header("User-Agent", "Mozilla/5.0")
            .header("X-APP-ID", "TEST_APP")
            .header("Content-Type", "application/json")
            .header("Build_version", versionWithStamp)
            .header("Platform", "Android")
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }

}