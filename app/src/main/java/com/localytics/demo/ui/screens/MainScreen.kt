package com.localytics.demo.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.localytics.demo.ui.bottomnav.BottomNavBar
import com.localytics.demo.ui.bottomnav.BottomNavItem
import com.localytics.demo.ui.bottomnav.MainScreenNavigationConfigurations

@Composable
fun MainScreen(activity: Activity) {
    val navController = rememberNavController()
    val bottomNavigationItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.CustomerId,
        BottomNavItem.Features,
        BottomNavItem.About
    )
    Scaffold(
        bottomBar = {
            BottomNavBar(navController, bottomNavigationItems)
        },
    ) {innerPadding ->
        MainScreenNavigationConfigurations(modifier = Modifier
            .padding(paddingValues = innerPadding)
            ,navController,activity)
    }
}