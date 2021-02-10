package ru.apteka.screen.aptekanew.presentation.viewmodel

import ru.apteka.base.BaseViewModel
import ru.apteka.base.binding.list.Diffable

class TitleItemViewModel(val title: String) : BaseViewModel(), Diffable {

    override fun isItemTheSameAs(other: Any): Boolean {
        return other is TitleItemViewModel
    }

    override fun isContentTheSameAs(other: Any): Boolean {
        return other is TitleItemViewModel && title == other.title
    }
}