package ru.apteka.base.binding

import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.apteka.AptekaApplication
import ru.apteka.base.binding.list.BaseAdapter
import ru.apteka.base.binding.list.BaseViewTypes
import kotlin.math.roundToInt


@BindingAdapter(value = ["items", "viewTypes", "itemDecoration", "diffCallback", "onSubmit"], requireAll = false)
fun setItems(view: RecyclerView,
             items: List<Any>?,
             viewTypes: BaseViewTypes,
             itemDecoration: RecyclerView.ItemDecoration?,
             diffCallback: DiffUtil.ItemCallback<Any>?,
             onSubmit: Runnable?) {
    if (view.adapter == null) {
        view.adapter = BaseAdapter(viewTypes, diffCallback)
        if (itemDecoration != null) view.addItemDecoration(itemDecoration)
    }
    if (items != null) {
        (view.adapter as BaseAdapter).submitList(items, onSubmit)
    }
}

@BindingAdapter("isRefreshing")
fun setRefreshState(layout: androidx.swiperefreshlayout.widget.SwipeRefreshLayout,
                    isRefreshing: Boolean) {
    layout.isRefreshing = isRefreshing
}

@BindingAdapter("onRefresh")
fun setOnRefreshListener(layout: androidx.swiperefreshlayout.widget.SwipeRefreshLayout,
                         listener: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener) {
    layout.setOnRefreshListener(listener)
}

@BindingAdapter(value = ["imageUrl", "requestOptions"], requireAll = false)
fun loadImage(view: ImageView, imageUrl: String?, requestOptions: RequestOptions?) {
    Glide.with(AptekaApplication.context)
        .load(imageUrl)
        .apply {
            if (requestOptions != null) {
                apply(requestOptions)
            }
        }
        .into(view)
}

@BindingAdapter("android:layout_marginLeft")
fun View.setLeftMargin(leftMargin: Float) {
    val layoutParams = this.layoutParams as MarginLayoutParams
    layoutParams.setMargins(leftMargin.roundToInt(), layoutParams.topMargin,
        layoutParams.rightMargin, layoutParams.bottomMargin)
    this.layoutParams = layoutParams
}

@BindingAdapter("android:layout_marginRight")
fun View.setRightMargin(rightMargin: Float) {
    val layoutParams = this.layoutParams as MarginLayoutParams
    layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin,
        rightMargin.roundToInt(), layoutParams.bottomMargin)
    this.layoutParams = layoutParams
}

@BindingAdapter("visibility")
fun View.setVisibility(value: Boolean?) {
    visibility = if (value == false) View.GONE else View.VISIBLE
}

@BindingAdapter("onNavigationBackClick")
fun Toolbar.onNavigationBackClick(listener: View.OnClickListener) {
    this.setNavigationOnClickListener(listener)
}

@BindingAdapter("idSrc")
fun ImageView.idSrc(@DrawableRes res: Int?) {
    res?.let {
        setImageResource(res)
    }
}
