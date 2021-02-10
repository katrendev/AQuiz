package ru.apteka.screen.categorylist.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ru.apteka.R
import ru.apteka.base.di.activityComponent
import ru.apteka.databinding.CategoryListFragmentBinding
import ru.apteka.screen.categorylist.di.CategoryListComponent
import ru.apteka.screen.categorylist.di.CategoryListModule
import ru.apteka.screen.categorylist.di.DaggerCategoryListComponent
import ru.apteka.screen.categorylist.presentation.router.CategoryListRouter
import ru.apteka.screen.categorylist.presentation.viewmodel.CategoryListViewModel
import javax.inject.Inject

class CategoryListFragment : androidx.fragment.app.Fragment() {

    val id: String? by lazy { arguments?.getString(CATEGORY_ID) }
    val name: String? by lazy { arguments?.getString(CATEGORY_NAME) }

    @Inject
    lateinit var viewModel: CategoryListViewModel

    @Inject
    lateinit var router: CategoryListRouter

    val component: CategoryListComponent by lazy {
        DaggerCategoryListComponent.builder()
            .categoryListModule(CategoryListModule(this, id, name))
            .mainComponent(activityComponent())
            .build()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
        router.bindViewModel(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return DataBindingUtil.inflate<CategoryListFragmentBinding>(inflater, R.layout.category_list_fragment, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DataBindingUtil.getBinding<CategoryListFragmentBinding>(view)?.apply {
            lifecycleOwner = this@CategoryListFragment
            vm = viewModel
        }
    }

    companion object {
        const val CATEGORY_ID = "CATEGORY_ID"
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }
}