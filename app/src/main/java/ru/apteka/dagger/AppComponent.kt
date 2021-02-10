package ru.apteka.dagger

import android.content.Context
import com.google.gson.Gson
import dagger.Component
import retrofit2.Retrofit
import ru.apteka.base.ResourceManager
import ru.apteka.base.commonapi.clients.AptekaRuApiClient
import ru.apteka.base.data.ISharedPreferenceManager
import ru.apteka.screen.categorylist.domain.CategoryListRepository
import ru.apteka.screen.main.domain.repository.BannersRepository
import javax.inject.Singleton

@Component(modules = [
    RemoteModule::class,
    AppModule::class,
    StorageModule::class,
    RepositoryModule::class
])
@Singleton
interface AppComponent {

    fun provideContext(): Context
    fun provideRetrofit(): Retrofit
    fun provideApi(): AptekaRuApiClient
    fun provideResourceManager(): ResourceManager
    fun provideCategoryListRepository(): CategoryListRepository
    fun provideISharedPreferenceManager(): ISharedPreferenceManager
    fun provideBannersRepository(): BannersRepository
    fun provideGson(): Gson
}