package ru.apteka.base.binding.list

import androidx.recyclerview.widget.DiffUtil

class BaseItemCallback : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when (oldItem) {
            is Diffable -> oldItem.isItemTheSameAs(newItem)
            else -> oldItem === newItem
        }
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any) : Boolean {
        return when (oldItem) {
            is Diffable -> oldItem.isContentTheSameAs(newItem)
            else -> oldItem == newItem
        }
    }
}