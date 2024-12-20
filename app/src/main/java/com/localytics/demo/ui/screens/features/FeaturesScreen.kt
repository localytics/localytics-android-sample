package com.localytics.demo.ui.screens.features

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.localytics.demo.ui.bottomnav.BottomNavItem
import com.localytics.demo.ui.screens.features.inbox.InboxActivity

@Composable
fun FeaturesScreen(
    modifier: Modifier, activity: Activity, navController: NavHostController
) {
    val items = listOf("Keys", "Host", "Profile", "Logs", "Inbox", "Places")

    val size = LocalConfiguration.current.screenWidthDp.dp / 2 // Calculate item size

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 16.dp, end = 16.dp, start = 16.dp)
    ) {
        Text(
            text = "Menu", fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )

        Text(
            text = "Localytics Features",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp, bottom = 16.dp),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 3 columns
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.fillMaxSize()
        ) {
            items(items.size) { index ->
                Card(elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
                    Box(
                        modifier = Modifier
                            .size(height = size / 2, width = size) // Set item size
                            .clickable {
                                when (index) {
                                    0 -> {
                                        navController.navigate(BottomNavItem.AppKeys.route)
                                    }

                                    1 -> {
                                        navController.navigate(BottomNavItem.Domains.route)
                                    }

                                    2 -> {
                                        navController.navigate(BottomNavItem.Profile.route)
                                    }

                                    3 -> {
                                        navController.navigate(BottomNavItem.Logs.route)
                                    }

                                    4 -> {
                                        activity.startActivity(
                                            Intent(
                                                activity, InboxActivity::class.java
                                            )
                                        )
                                    }

                                    else -> {
                                        navController.navigate(BottomNavItem.PLaces.route)
                                    }
                                }
                            }, contentAlignment = Alignment.Center // Center content
                    ) {
                        Text(text = items[index])
                    }
                }
            }
        }
    }
}