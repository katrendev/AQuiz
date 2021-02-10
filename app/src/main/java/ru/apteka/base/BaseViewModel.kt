package ru.apteka.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import ru.apteka.utils.putValue

open class BaseViewModel : ViewModel(), LifecycleOwner {

    private val lifecycle: LifecycleRegistry = LifecycleRegistry(this)

    protected val disposable = CompositeDisposable()

    open val isError = MutableLiveData<Boolean>().putValue(false)


    init {
        lifecycle.currentState = Lifecycle.State.RESUMED
    }

    override fun getLifecycle() = lifecycle

    public override fun onCleared() {
        lifecycle.currentState = Lifecycle.State.DESTROYED
        disposable.clear()
    }

    open fun onBind(dataBinding: ViewDataBinding) {}

    protected fun recordException(throwable: Throwable) {}

}