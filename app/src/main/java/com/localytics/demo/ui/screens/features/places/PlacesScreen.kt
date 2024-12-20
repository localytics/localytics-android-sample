package com.localytics.demo.ui.screens.features.places

import android.Manifest
import android.app.Activity
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.localytics.demo.utils.hasBackgroundLocationPermission
import com.localytics.demo.utils.hasLocationPermissions

@Composable
fun PlacesScreen(modifier: Modifier, activity: Activity, navController: NavHostController) {

    val locationPermission = remember {
        mutableStateOf(hasLocationPermissions(activity))
    }
    val backgroundPermission = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            mutableStateOf(hasBackgroundLocationPermission(activity))
        } else {
            mutableStateOf(true)
        }
    }

    val locationLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                locationPermission.value = isGranted
            })

    val backgroundLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                backgroundPermission.value = isGranted
            })


    if (!locationPermission.value) {
        LaunchedEffect(Unit) {
            locationLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            locationLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    if (locationPermission.value && !backgroundPermission.value && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        LaunchedEffect(Unit) {
            backgroundLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
    }
    Column {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight().padding(32.dp)) {
            LocationPermissionMessage(backgroundPermission.value, locationPermission.value,activity)
        }
    }
}