package ru.apteka.screen.aptekanew.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import ru.apteka.R
import ru.apteka.base.di.appComponent
import ru.apteka.base.safeSubcribe
import ru.apteka.base.view.SearchActionsFragment
import ru.apteka.databinding.NewAptekaFragmentBinding
import ru.apteka.screen.aptekanew.di.DaggerNewAptekaComponent
import ru.apteka.screen.aptekanew.di.NewAptekaComponent
import ru.apteka.screen.aptekanew.di.NewAptekaModule
import ru.apteka.screen.aptekanew.presentation.viewmodel.NewAptekaViewModel
import ru.apteka.screen.categorylist.presentation.view.CategoryListFragment
import java.util.*
import javax.inject.Inject

class NewAptekaFragment : SearchActionsFragment() {

    @Inject
    lateinit var viewModel: NewAptekaViewModel

    lateinit var binding: NewAptekaFragmentBinding

    val component: NewAptekaComponent by lazy {
        DaggerNewAptekaComponent.builder()
            .newAptekaModule(NewAptekaModule(this))
            .appComponent(appComponent())
            .build()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.new_apteka_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            lifecycleOwner = this@NewAptekaFragment
            vm = viewModel.apply {
                aptekaSearchViewModel.openSearch.safeSubcribe(this@NewAptekaFragment) {  }
                aptekaSearchViewModel.openMicrophone.safeSubcribe(this@NewAptekaFragment) { displaySpeechRecognizer() }
                aptekaSearchViewModel.openScanner.safeSubcribe(this@NewAptekaFragment) { openScanner() }

                openWebView.observe(viewLifecycleOwner) { url ->
                }
                openMiniShop.observe(viewLifecycleOwner) { url ->
                }
                openCategoryAdditional.observe(viewLifecycleOwner) { category ->
                }
                openAllCategories.observe(viewLifecycleOwner) {
                    val bundleOf = bundleOf(
                        CategoryListFragment.CATEGORY_NAME to "Все категории"
                    )
                    findNavController().navigate(R.id.to_category_list, bundleOf)
                }
                openCategoryList.observe(viewLifecycleOwner) { category ->
                    val bundleOf = bundleOf(
                        CategoryListFragment.CATEGORY_ID to category.searchUrl,
                        CategoryListFragment.CATEGORY_NAME to category.name
                    )
                    findNavController().navigate(R.id.to_category_list, bundleOf)
                }
                openProductsForCategory.observe(viewLifecycleOwner) { category ->
                }
            }

            list.layoutManager = GridLayoutManager(list.context, 6).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return viewModel.getSpanSize(position)
                    }
                }
            }
        }
    }
}