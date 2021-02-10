package ru.apteka.screen.aptekanew.di

import dagger.Component
import ru.apteka.base.di.PerFragment
import ru.apteka.dagger.AppComponent
import ru.apteka.screen.aptekanew.presentation.view.NewAptekaFragment

@PerFragment
@Component(
        modules = [NewAptekaModule::class],
        dependencies = [AppComponent::class]
)
interface NewAptekaComponent {
    fun inject(fragment: NewAptekaFragment)
}