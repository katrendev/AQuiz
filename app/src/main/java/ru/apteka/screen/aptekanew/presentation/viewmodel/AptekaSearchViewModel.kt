package ru.apteka.screen.aptekanew.presentation.viewmodel

import ru.apteka.base.BaseViewModel
import ru.apteka.base.SingleLiveEvent
import ru.apteka.base.call

class AptekaSearchViewModel : BaseViewModel() {
    val openSearch = SingleLiveEvent<Unit>()
    val openMicrophone = SingleLiveEvent<Unit>()
    val openScanner = SingleLiveEvent<Unit>()

    fun onSearchClick() {
        openSearch.call()
    }

    fun onMicClick() {
        openMicrophone.call()
    }

    fun onScannerClick() {
        openScanner.call()
    }
}