package ru.apteka.base.view

import android.graphics.Color
import java.lang.Exception

object BaseConverter {
    @JvmStatic
    fun parseColor(colorHex: String?): Int {
        return try {
            Color.parseColor(colorHex)
        } catch (e: Exception) {
            Color.WHITE
        }
    }

    @JvmStatic
    fun intToFloat(int: Int?): Float {
        return int?.toFloat() ?: 0f
    }

    @JvmStatic
    fun intToString(int: Int?): String {
        return int?.toString() ?: ""
    }
}