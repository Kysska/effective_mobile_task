package com.example.effective_mobile_task.utils

import android.content.Context
import android.view.MenuItem
import androidx.annotation.OptIn
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils

@OptIn(ExperimentalBadgeUtils::class)
fun MenuItem.updateBadge(count: Int, context: Context) {
    val actionView = this.actionView ?: return

    val badgeDrawable = BadgeDrawable.create(context).apply {
        number = count
        isVisible = true
    }

    BadgeUtils.attachBadgeDrawable(badgeDrawable, actionView)
}
