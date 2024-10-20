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

fun TextView.setFormattedText(resId: Int, count: Int) {
    val template = context.getString(resId, count)
    when {
        count % 10 == 1 && count % 100 != 11 -> {
            this.text = String.format(template, count)
        }
        count % 10 in 2..4 && count % 100 !in 12..14 -> {
            this.text = String.format(template, count)
        }
        else -> {
            this.text = String.format(template, count)
        }
    }
}

fun Button.setFormattedText(resId: Int, count: Int) {
    val template = context.getString(resId, count)
    when {
        count % 10 == 1 && count % 100 != 11 -> {
            this.text = String.format(template, count)
        }
        count % 10 in 2..4 && count % 100 !in 12..14 -> {
            this.text = String.format(template, count)
        }
        else -> {
            this.text = String.format(template, count)
        }
    }
}

fun TextView.setFormattedText(resId: Int, vararg formatArgs: Any?) {
    this.text = context.getString(resId, *formatArgs)
}

fun Button.setFormattedText(resId: Int, vararg formatArgs: Any?) {
    this.text = context.getString(resId, *formatArgs)
}
