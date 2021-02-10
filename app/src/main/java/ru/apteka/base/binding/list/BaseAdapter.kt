package ru.apteka.base.binding.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

open class BaseAdapter(
    private val viewTypes: BaseViewTypes,
    diffCallback: DiffUtil.ItemCallback<Any>?
) : ListAdapter<Any, BaseViewHolder>(diffCallback ?: BaseItemCallback()) {

    var items: MutableList<Any> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(inflateView(parent, viewTypes.getLayout(viewType)))

    override fun getItemViewType(position: Int) = viewTypes.getViewType(getItem(position)::class)

    fun getItemLayout(position: Int) = viewTypes.getLayout(getItemViewType(position))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun inflateView(parent: ViewGroup, layout: Int): View {
        val inflater = LayoutInflater.from(parent.context)

        DataBindingUtil.inflate<ViewDataBinding>(inflater, layout, parent, false)?.apply {
            return root
        }

        return inflater.inflate(layout, parent, false)
    }

    override fun onViewRecycled(holder: BaseViewHolder) {
        holder.clear()
        super.onViewRecycled(holder)
    }

    override fun submitList(list: List<Any>?, submitCallback: Runnable?) {
        super.submitList(list, submitCallback)
        items.clear()
        list?.let { items.addAll(it) }
    }
}