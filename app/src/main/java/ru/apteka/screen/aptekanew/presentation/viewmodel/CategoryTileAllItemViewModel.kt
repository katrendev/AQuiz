package ru.apteka.screen.aptekanew.presentation.viewmodel

import ru.apteka.base.BaseViewModel
import ru.apteka.base.SingleLiveEvent
import ru.apteka.base.call

class CategoryTileAllItemViewModel : BaseViewModel() {
    val click = SingleLiveEvent<Unit>()

    fun click() {
        click.call()
    }
}