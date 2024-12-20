package com.localytics.demo.ui.bottomnav

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.localytics.demo.ui.screens.AboutScreen
import com.localytics.demo.ui.screens.home.HomeScreen
import com.localytics.demo.ui.screens.features.FeaturesScreen
import com.localytics.demo.ui.screens.cutomerid.CustomerIdScreen
import com.localytics.demo.ui.screens.features.domain.DomainConfiguration
import com.localytics.demo.ui.screens.features.keys.AppKeysScreen
import com.localytics.demo.ui.screens.features.logs.EnableLogging
import com.localytics.demo.ui.screens.features.places.PlacesScreen
import com.localytics.demo.ui.screens.features.profile.ProfileAttributes

@Composable
fun MainScreenNavigationConfigurations(
    modifier: Modifier = Modifier, navController: NavHostController, activity: Activity
) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(modifier, BottomNavItem.Home.route)
        }
        composable(BottomNavItem.CustomerId.route) {
            CustomerIdScreen(modifier)
        }
        composable(BottomNavItem.Features.route) {
            FeaturesScreen(modifier,activity,navController)
        }
        composable(BottomNavItem.About.route) {
            AboutScreen(modifier)
        }
        composable(BottomNavItem.Logs.route) {
            EnableLogging(modifier, activity, navController)
        }
        composable(BottomNavItem.Profile.route) {
            ProfileAttributes(modifier, activity, navController)
        }
        composable(BottomNavItem.Domains.route) {
            DomainConfiguration(modifier, activity, navController)
        }
        composable(BottomNavItem.AppKeys.route) {
            AppKeysScreen(modifier, activity, navController)
        }
        composable(BottomNavItem.PLaces.route){
            PlacesScreen(modifier, activity, navController)
        }
    }
}





