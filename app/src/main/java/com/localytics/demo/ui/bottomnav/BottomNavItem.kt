package com.localytics.demo.ui.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object CustomerId : BottomNavItem("customer_id", Icons.Default.Person, "Customer ID")
    object Features : BottomNavItem("features", Icons.Default.Settings, "Features")
    object About : BottomNavItem("about", Icons.Default.Info, "About")
    object Logs : BottomNavItem("logs", Icons.Default.Lock, "Logs")
    object Profile : BottomNavItem("profile", Icons.Default.Lock, "Profile")
    object Domains : BottomNavItem("domains", Icons.Default.Lock, "Domain")
    object AppKeys : BottomNavItem("appKeys", Icons.Default.Lock, "AppKeys")
    object PLaces : BottomNavItem("places", Icons.Default.Lock, "Places")

}