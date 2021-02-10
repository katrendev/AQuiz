package ru.apteka.screen.categorylist.presentation.view

import ru.apteka.R
import ru.apteka.base.binding.list.BaseViewTypes
import ru.apteka.screen.categorylist.presentation.viewmodel.CategoryItemViewModel

class CategoryItemViewTypes private constructor() : BaseViewTypes() {

    init {
        addViewType(CategoryItemViewModel::class, R.layout.category_item)
    }

    companion object {
        @JvmStatic
        val instance = CategoryItemViewTypes()
    }
}