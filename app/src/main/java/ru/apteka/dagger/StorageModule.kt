package ru.apteka.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.apteka.base.data.ISharedPreferenceManager
import ru.apteka.base.data.SharedPreferencesManager
import javax.inject.Singleton

@Module
class StorageModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesSharedPreferenceManager(): ISharedPreferenceManager =
        SharedPreferencesManager(context)
}