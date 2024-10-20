package com.example.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText

class CustomViewInputEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputEditText(context, attrs, defStyleAttr) {

    fun setHintText(resId: Int) {
        hint = null
        val text = ContextCompat.getString(context, resId)
        hint = text
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }
}
