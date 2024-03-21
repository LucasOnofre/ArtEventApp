package com.onoffrice.artevent.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes
import com.onoffrice.artwork.R

fun Context.createDialog(
    @StringRes title: Int,
    message: String,
    show: Boolean = true,
    @StringRes positiveButton: Int,
    positiveClick: (DialogInterface) -> Unit
) {
    AlertDialog.Builder(this, R.style.AlertDialogTheme)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButton) { dialog, _ ->
            positiveClick.invoke(dialog)
        }
        .create().apply { if (show) show() }
}