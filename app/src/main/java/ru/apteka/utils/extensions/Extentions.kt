package ru.apteka.utils.worker

import android.content.Context
import androidx.appcompat.app.AlertDialog
import ru.apteka.R


fun Context.showServerErrorAlert(repeatAction: (() -> Unit)? = null) {
    val builder = AlertDialog.Builder(this)
        .setCancelable(false)
        .setMessage(R.string.server_error_alert_message)
        .setNegativeButton(when (repeatAction) {
            null -> R.string.good
            else -> R.string.cancellation
        }, null)

    if (repeatAction != null) {
        builder.setPositiveButton(R.string.repeat) { _, _ ->
            repeatAction.invoke()
        }
    }

    builder.show()
}
