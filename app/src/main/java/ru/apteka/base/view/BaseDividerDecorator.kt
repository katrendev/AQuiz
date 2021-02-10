package ru.apteka.base.view

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View


open class BaseDividerDecorator(context: Context, sideOffsetDp: Int = 16, innerOffsetDp: Int = 16) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
    private val offsetSide: Int = convertDpToPixel(sideOffsetDp, context)
    private val offsetInner: Int = convertDpToPixel(innerOffsetDp, context)
    override fun getItemOffsets(outRect: Rect, view: View, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        parent.adapter?.let { adapter ->
            when (parent.getChildAdapterPosition(view)) {
                0 -> when (adapter.itemCount) {
                    1 -> {
                        outRect.left = offsetSide
                        outRect.right = offsetSide
                    }
                    else -> outRect.left = offsetSide
                }
                adapter.itemCount - 1 -> {
                    outRect.left = offsetInner
                    outRect.right = offsetSide
                }
                else -> outRect.left = offsetInner
            }
        }
    }

    private fun convertDpToPixel(dp: Int, context: Context): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }

    companion object {
        @JvmStatic
        fun create(context: Context, offsetSide: Int, offsetInner: Int) = BaseDividerDecorator(context, offsetSide, offsetInner)

        @JvmStatic
        fun create(context: Context) = BaseDividerDecorator(context)
    }
}

