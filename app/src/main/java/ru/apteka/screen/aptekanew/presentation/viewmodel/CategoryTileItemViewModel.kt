package ru.apteka.screen.aptekanew.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.apteka.base.BaseViewModel
import ru.apteka.base.SingleLiveEvent
import ru.apteka.base.call
import java.util.*

class CategoryTileItemViewModel(
    private val categoryName: String?,
    private val categoryIconUrl: String? = null
) : BaseViewModel() {
    val locale = Locale("ru", "RU")

    val name = MutableLiveData<String>().apply { value = categoryName?.toLowerCase(locale)?.capitalize(locale) }
    val icon = MutableLiveData<String>().apply {
        categoryIconUrl?.let { value = it }
    }

    val click = SingleLiveEvent<Unit>()

    fun click() {
        click.call()
    }
}