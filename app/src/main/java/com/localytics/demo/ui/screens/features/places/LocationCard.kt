package com.localytics.demo.ui.screens.features.places

import android.app.Activity
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.localytics.androidx.Localytics
import java.io.IOException
import java.util.Locale

@Composable
fun LocationCard(location: Location?, activity: Activity) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Current Location", style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (location != null) {
                Text("Latitude: ${location.latitude}")
                Text("Longitude: ${location.longitude}")
                Localytics.setLocation(location)
                // Optionally, display address or other location details
            } else {
                CircularProgressIndicator() // Show loading indicator while fetching location
            }
        }
    
}
