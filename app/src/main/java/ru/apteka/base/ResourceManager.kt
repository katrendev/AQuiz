package ru.apteka.base

import android.content.Context
import android.content.res.Resources
import androidx.annotation.BoolRes
import androidx.annotation.StringRes

class ResourceManager(val context: Context) {

    fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }

    fun getString(@StringRes id: Int, formatArgs: Any): String {
        return context.getString(id, formatArgs)
    }

    fun getBoolean(@BoolRes id: Int): Boolean {
        return try {
            context.resources.getBoolean(id)
        } catch (e: Resources.NotFoundException) {
            false
        } catch (e: Exception) {
            false
        }
    }
}