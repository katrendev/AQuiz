package ru.apteka.screen.aptekanew.di

import dagger.Module
import dagger.Provides
import ru.apteka.base.data.ISharedPreferenceManager
import ru.apteka.base.di.PerFragment
import ru.apteka.base.viewModelProvide
import ru.apteka.screen.aptekanew.presentation.view.NewAptekaFragment
import ru.apteka.screen.aptekanew.presentation.viewmodel.NewAptekaViewModel
import ru.apteka.screen.categorylist.domain.CategoryListInteractor
import ru.apteka.screen.categorylist.domain.CategoryListRepository
import ru.apteka.screen.main.domain.interactor.BannersInteractor
import ru.apteka.screen.main.domain.interactor.BannersInteractorImpl
import ru.apteka.screen.main.domain.repository.BannersRepository

@Module
class NewAptekaModule(
    private val fragment: NewAptekaFragment
) {

    @PerFragment
    @Provides
    fun provideNewAptekaViewModel(
        bannersInteractor: BannersInteractor,
        categoryListInteractor: CategoryListInteractor
    ): NewAptekaViewModel {
        return fragment.viewModelProvide {
            NewAptekaViewModel(bannersInteractor, categoryListInteractor)
        }
    }

    @PerFragment
    @Provides
    fun provideCategoryListInteractor(repository: CategoryListRepository): CategoryListInteractor {
        return CategoryListInteractor(repository)
    }

    @PerFragment
    @Provides
    fun provideBannersInteractor(prefs: ISharedPreferenceManager, bannersRepository: BannersRepository): BannersInteractor {
        return BannersInteractorImpl(prefs, bannersRepository)
    }
}