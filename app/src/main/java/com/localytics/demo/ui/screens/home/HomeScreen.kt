package com.localytics.demo.ui.screens.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.localytics.androidx.Localytics
import com.localytics.demo.R
import com.localytics.demo.utils.hasNotificationPermission

@Composable
fun HomeScreen(modifier: Modifier = Modifier, route: String) {
//    Localytics.tagScreen("Home Screen")
    val context = LocalContext.current
    val homeViewModel = HomeViewModel()

    val hasNotificationPermission = remember {
        mutableStateOf(hasNotificationPermission(context))
    }
    Localytics.registerPush()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasNotificationPermission.value = isGranted
        })


    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 16.dp, end = 16.dp, start = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.localytics),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
        Text(
            text = stringResource(R.string.push),
            modifier = Modifier.padding(top = 32.dp),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        Card(elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            onClick = {
                if (!hasNotificationPermission.value) {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }) {
            PushFeatureUi(hasNotificationPermission.value, homeViewModel)
        }

        Text(
            text = stringResource(R.string.in_app_message),
            modifier = Modifier.padding(top = 32.dp),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
        ) {
            InAppUi(homeViewModel)
        }

        Text(
            text = stringResource(R.string.inbox_messaging),
            modifier = Modifier.padding(top = 32.dp),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
        ) {
            InboxUi(homeViewModel)
        }

        Text(
            text = stringResource(R.string.events),
            modifier = Modifier.padding(top = 32.dp),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            EventsUi(homeViewModel)
        }
    }
}








