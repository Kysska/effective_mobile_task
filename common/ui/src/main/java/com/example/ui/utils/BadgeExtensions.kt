package com.example.ui.utils

import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.showBadge(menuItemId: Int, count: Int) {
    this.getOrCreateBadge(menuItemId).apply {
        isVisible = count > 0
        number = count
    }
}

fun BottomNavigationView.hideBadge(menuItemId: Int) {
    this.removeBadge(menuItemId)
}
