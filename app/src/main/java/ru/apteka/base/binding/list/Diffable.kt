package ru.apteka.base.binding.list

interface Diffable {
    fun isItemTheSameAs(other: Any): Boolean
    fun isContentTheSameAs(other: Any): Boolean
}