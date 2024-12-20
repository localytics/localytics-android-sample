package com.localytics.demo.ui.screens.features.places

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

@Composable
fun LocationPermissionMessage(
    backgroundPermission: Boolean, locationPermission: Boolean, activity: Activity
) {

    Card(modifier = Modifier.fillMaxWidth()) {

        if (!backgroundPermission && !locationPermission) {
            AnimatedVisibility(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                visible = true,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column {
                    Text(
                        text = "This feature requires location permission to function correctly.",
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        color = Color.White
                    )
                }
            }

        } else {
            FetchLocation(activity)
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun FetchLocation(activity: Activity) {
    var currentLocation by remember { mutableStateOf<Location?>(null) }
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(activity) }
    val locationCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    // Update location state here
                    currentLocation = location
                }
            }
        }
    }


    DisposableEffect(key1 = Unit) {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000).build()

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
        // Cleanup: Remove location updates when LaunchedEffect leaves the composition
        onDispose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }

    }

    LocationCard(location = currentLocation,activity)
}
