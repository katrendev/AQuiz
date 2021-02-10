package ru.apteka.screen.categorylist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.apteka.base.BaseViewModel
import ru.apteka.base.SingleLiveEvent
import ru.apteka.base.call
import ru.apteka.screen.categorylist.model.domain.CatalogCategory

class CategoryItemViewModel(
    private val category: CatalogCategory
) : BaseViewModel() {

    val height = MutableLiveData<Int>().apply {
        value = category.height()
    }
    val name = MutableLiveData<String>().apply { value = category.name }
    val icon = MutableLiveData<String>().apply { value = category.imageUrl }
    val hasExpander = MutableLiveData<Boolean>().apply {
        value = !category.subGroup.isNullOrEmpty()
    }
    val click = SingleLiveEvent<Unit>()
    val children = mutableListOf<CategoryItemViewModel>()
    val isExpanded = MutableLiveData<Boolean>()
    fun click() {
        click.call()
    }

    fun isLeaf() = children.isEmpty()
    fun isExpand() = isExpanded.value ?: false
    fun toggle() = isExpanded.postValue(!isExpand())
}