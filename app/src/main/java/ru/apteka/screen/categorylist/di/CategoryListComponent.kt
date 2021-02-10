package ru.apteka.screen.categorylist.di

import dagger.Component
import ru.apteka.base.di.PerFragment
import ru.apteka.screen.categorylist.presentation.view.CategoryListFragment
import ru.apteka.screen.main.di.MainComponent

@PerFragment
@Component(
        modules = [CategoryListModule::class],
        dependencies = [MainComponent::class]
)
interface CategoryListComponent {
    fun inject(fragment: CategoryListFragment)
}