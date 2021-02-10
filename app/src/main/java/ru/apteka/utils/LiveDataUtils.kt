package ru.apteka.utils

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.putValue(value: T?): MutableLiveData<T> {
    this.value = value
    return this
}
