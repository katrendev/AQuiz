package ru.apteka.screen.categorylist.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import ru.apteka.base.di.PerFragment
import ru.apteka.base.viewModelProvide
import ru.apteka.screen.categorylist.domain.CategoryListInteractor
import ru.apteka.screen.categorylist.domain.CategoryListRepository
import ru.apteka.screen.categorylist.presentation.router.CategoryListRouter
import ru.apteka.screen.categorylist.presentation.viewmodel.CategoryListViewModel

@Module
class CategoryListModule(
    private val fragment: Fragment,
    private val categoryId: String?,
    private val name: String?
) {

    @PerFragment
    @Provides
    fun provideInteractor(repository: CategoryListRepository): CategoryListInteractor {
        return CategoryListInteractor(repository)
    }

    @PerFragment
    @Provides
    fun provideRouter(): CategoryListRouter {
        return CategoryListRouter(fragment)
    }

    @PerFragment
    @Provides
    fun provideViewModel(
        interactor: CategoryListInteractor
    ): CategoryListViewModel {
        return fragment.viewModelProvide {
            CategoryListViewModel(interactor, categoryId, name)
        }
    }
}