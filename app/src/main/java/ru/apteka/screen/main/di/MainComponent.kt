package ru.apteka.screen.main.di

import android.content.Context
import dagger.Component
import ru.apteka.base.commonapi.clients.AptekaRuApiClient
import ru.apteka.base.data.ISharedPreferenceManager
import ru.apteka.base.di.PerActivity
import ru.apteka.dagger.AppComponent
import ru.apteka.screen.categorylist.domain.CategoryListRepository
import ru.apteka.screen.main.domain.repository.BannersRepository
import ru.apteka.screen.main.presentation.view.MainActivity

@PerActivity
@Component(
    modules = [MainModule::class],
    dependencies = [AppComponent::class]
)
interface MainComponent {
    fun inject(activity: MainActivity)
    fun provideActivity(): MainActivity
    fun provideContext(): Context
    fun provideApi(): AptekaRuApiClient
    fun provideISharedPreferenceManager(): ISharedPreferenceManager
    fun provideCategoryListRepository(): CategoryListRepository
    fun provideBannersRepository(): BannersRepository
}