package ru.apteka.screen.aptekanew.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.apteka.base.BaseViewModel
import ru.apteka.base.SingleLiveEvent
import ru.apteka.base.call
import ru.apteka.screen.aptekanew.domain.model.BannerInfoModel

class BannerTileItemViewModel(
    private val banner: BannerInfoModel,
    val isFirstColumn: Boolean = false
) : BaseViewModel() {

    val imageUrl = MutableLiveData<String>().apply { value = banner.photoPath }

    val click = SingleLiveEvent<Unit>()

    fun click() {
        click.call()
    }
}