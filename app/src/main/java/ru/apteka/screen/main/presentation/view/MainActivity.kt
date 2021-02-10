package ru.apteka.screen.main.presentation.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.main_activity.*
import ru.apteka.R
import ru.apteka.base.di.ComponentHolder
import ru.apteka.base.di.appComponent
import ru.apteka.base.view.BaseActivity
import ru.apteka.databinding.MainActivityBinding
import ru.apteka.screen.main.di.DaggerMainComponent
import ru.apteka.screen.main.di.MainComponent
import ru.apteka.screen.main.di.MainModule
import ru.apteka.screen.main.presentation.router.MainRouter
import ru.apteka.screen.main.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity(), ComponentHolder<MainComponent> {

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var router: MainRouter

    val navigation: NavController by lazy { Navigation.findNavController(this, R.id.main_host_fragment) }

    override val component: MainComponent by lazy {
        DaggerMainComponent.builder()
            .appComponent(appComponent())
            .mainModule(MainModule(this@MainActivity))
            .build()
    }

    private val binding: MainActivityBinding by lazy {
        DataBindingUtil.inflate<MainActivityBinding>(layoutInflater, R.layout.main_activity, null, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        router.bindViewModel(viewModel)
        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
        }
    }

    override fun onSupportNavigateUp() =
        navigation.navigateUp()
}