package ru.apteka.screen.aptekanew.presentation.view

import ru.apteka.R
import ru.apteka.base.binding.list.BaseViewTypes
import ru.apteka.screen.aptekanew.presentation.viewmodel.BannerTileItemViewModel
import ru.apteka.screen.aptekanew.presentation.viewmodel.CategoryTileAllItemViewModel
import ru.apteka.screen.aptekanew.presentation.viewmodel.CategoryTileItemViewModel
import ru.apteka.screen.aptekanew.presentation.viewmodel.TitleItemViewModel

class NewAptekaItemViewTypes private constructor() : BaseViewTypes() {

    init {
        addViewType(CategoryTileItemViewModel::class, R.layout.category_tile_item)
        addViewType(CategoryTileAllItemViewModel::class, R.layout.category_tile_all_item)
        addViewType(TitleItemViewModel::class, R.layout.title_item)
        addViewType(BannerTileItemViewModel::class, R.layout.banner_tile_item)
    }

    companion object {
        @JvmStatic
        val instance = NewAptekaItemViewTypes()
    }
}