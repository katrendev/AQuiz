package ru.apteka.dagger

import dagger.Module
import dagger.Provides
import ru.apteka.base.commonapi.clients.AptekaRuApiClient
import ru.apteka.screen.categorylist.data.repository.CategoryListRepositoryImpl
import ru.apteka.screen.categorylist.domain.CategoryListRepository
import ru.apteka.screen.main.data.BannersRepositoryImpl
import ru.apteka.screen.main.domain.repository.BannersRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCategoryListRepository(api: AptekaRuApiClient): CategoryListRepository {
        return CategoryListRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideBannersRepository(
        apiClient: AptekaRuApiClient
    ): BannersRepository {
        return BannersRepositoryImpl(apiClient)
    }
}