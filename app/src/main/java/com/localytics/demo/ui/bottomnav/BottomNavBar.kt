package com.localytics.demo.ui.bottomnav

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun BottomNavBar(navController: NavHostController, bottomNavigationItems: List<BottomNavItem>) {
    BottomNavigation {
        val currentRoute = remember {
            mutableStateOf("")
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = destination.route!!
        }
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(selected = (currentRoute.value == screen.route),
                icon = { Icon(imageVector = screen.icon, contentDescription = "hgd") },
                label = {
                    Text(
                        text = screen.label,
                        color = (if (currentRoute.value == screen.route) Color.White else Color.DarkGray)
                    )
                },
                modifier = Modifier.background(Color.Black),
                selectedContentColor = Color.White,
                unselectedContentColor = Color.DarkGray,
                alwaysShowLabel = true, // This hides the title for the unselected items
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute.value != screen.route) {
                        navController.navigate(screen.route)
                    }
                })
        }
    }
}