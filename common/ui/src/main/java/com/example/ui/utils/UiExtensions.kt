package com.example.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.TextView

fun Context.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    startActivity(intent)
}

fun Button.setTextOrHide(text: String?) {
    if (text.isNullOrEmpty()) {
        visibility = View.GONE
    } else {
        this.text = text
        visibility = View.VISIBLE
    }
}

fun TextView.setFormattedText(resId: Int, vararg formatArgs: Any?) {
    this.text = context.getString(resId, *formatArgs)
}

fun Button.setFormattedText(resId: Int, vararg formatArgs: Any?) {
    this.text = context.getString(resId, *formatArgs)
}
