package com.angad.collegeapp.models

import androidx.compose.ui.graphics.vector.ImageVector

//    For bottom navigation
data class NavItem(
    val title: String,
    val icon: Int
)

data class BottomNavItem(
        val title: String,
        val icon: Int,
        val route: String
)