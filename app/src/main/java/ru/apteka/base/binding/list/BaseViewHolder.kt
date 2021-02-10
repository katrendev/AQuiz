package ru.apteka.base.binding.list

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import ru.apteka.BR
import ru.apteka.BuildConfig
import ru.apteka.base.BaseViewModel

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LifecycleOwner {

    val binding = DataBindingUtil.getBinding<ViewDataBinding>(itemView)

    fun bind(viewModel: Any?) {
        lifecycle.currentState = Lifecycle.State.RESUMED
        binding?.setVariable(BR.vm, viewModel)
        binding?.executePendingBindings()
        binding?.lifecycleOwner = this
        try {
            binding?.let { (viewModel as BaseViewModel).onBind(it) }
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
        }
    }

    private val lifecycle: LifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle() = lifecycle

    fun clear() {
        lifecycle.currentState = Lifecycle.State.DESTROYED
        binding?.unbind()
        binding?.setVariable(BR.vm, null)
        binding?.executePendingBindings()
        binding?.lifecycleOwner = null
    }
}