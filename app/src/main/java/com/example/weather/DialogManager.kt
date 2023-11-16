package com.example.weather

import android.app.AlertDialog
import android.content.Context

object DialogManager {
    fun locationSettingsDialog(context: Context, listener: Listener) {
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        dialog.setTitle("Enable gps?")
        dialog.setMessage("Enable disabled, do you want enable it?")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes") { _,_ ->
            listener.onClick()
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { _,_ ->
            dialog.dismiss()
        }
        dialog.show()
    }

    interface Listener {
        fun onClick()
    }
}