package ru.apteka.screen.main.di

import dagger.Module
import dagger.Provides
import ru.apteka.base.data.ISharedPreferenceManager
import ru.apteka.base.di.PerActivity
import ru.apteka.base.viewModelProvide
import ru.apteka.screen.main.domain.interactor.BannersInteractor
import ru.apteka.screen.main.domain.interactor.BannersInteractorImpl
import ru.apteka.screen.main.domain.repository.BannersRepository
import ru.apteka.screen.main.presentation.router.MainRouter
import ru.apteka.screen.main.presentation.view.MainActivity
import ru.apteka.screen.main.presentation.viewmodel.MainViewModel

@Module
class MainModule(
    private val activity: MainActivity
) {

    @PerActivity
    @Provides
    fun provideVM(
    ): MainViewModel {
        return activity.viewModelProvide {
            MainViewModel()
        }
    }

    @PerActivity
    @Provides
    fun provideRouter(): MainRouter {
        return MainRouter(activity)
    }

    @PerActivity
    @Provides
    fun provideBannersInteractor(prefs: ISharedPreferenceManager, bannersRepository: BannersRepository): BannersInteractor {
        return BannersInteractorImpl(prefs, bannersRepository)
    }

    @PerActivity
    @Provides
    fun provideMainActivity(): MainActivity {
        return activity
    }
}