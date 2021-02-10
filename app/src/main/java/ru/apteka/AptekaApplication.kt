package ru.apteka

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import ru.apteka.base.di.ComponentHolder
import ru.apteka.dagger.*

class AptekaApplication : MultiDexApplication(), ComponentHolder<AppComponent> {

    override val component: AppComponent by lazy {
        appComponent
    }

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var context: AptekaApplication
    }

    override fun onCreate() {
        super.onCreate()

        context = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .storageModule(StorageModule(this))
            .remoteModule(RemoteModule())
            .build()


        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }

        if (BuildConfig.DEBUG) {
//            LeakCanary.install(this)
            Stetho.initializeWithDefaults(this)
//            StrictMode.enableDefaults()
        }

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}