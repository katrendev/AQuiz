package ru.apteka.screen.categorylist.presentation.router

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import ru.apteka.R
import ru.apteka.base.safeSubcribe
import ru.apteka.screen.categorylist.presentation.view.CategoryListFragment
import ru.apteka.screen.categorylist.presentation.viewmodel.CategoryListViewModel
import ru.apteka.utils.worker.showServerErrorAlert

class CategoryListRouter(
    private val fragment: Fragment
) {

    private val navController by lazy { NavHostFragment.findNavController(fragment) }

    fun bindViewModel(viewModel: CategoryListViewModel) {
        viewModel.backClick.safeSubcribe(fragment) {
            navController.popBackStack()
        }

        viewModel.categoryClick.observe(fragment, { category ->
        })

        viewModel.rootCategoryClick.observe(fragment) {
            val bundleOf = bundleOf(
                CategoryListFragment.CATEGORY_ID to it.searchUrl,
                CategoryListFragment.CATEGORY_NAME to it.name
            )
            navController.navigate(R.id.to_category_list, bundleOf)
        }

        viewModel.categoryAdditionalClick.observe(fragment) {
        }

        viewModel.isError.safeSubcribe(fragment) { showDialog ->
            if (showDialog) {
                fragment.requireContext().showServerErrorAlert {
                    viewModel.refresh()
                }
            }
        }
    }
}