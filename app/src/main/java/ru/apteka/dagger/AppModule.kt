package ru.apteka.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.apteka.base.ResourceManager
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideResourceManager(): ResourceManager =
        ResourceManager(app)
}