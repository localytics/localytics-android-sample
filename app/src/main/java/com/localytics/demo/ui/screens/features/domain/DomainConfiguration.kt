package com.localytics.demo.ui.screens.features.domain

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.localytics.androidx.Localytics
import com.localytics.demo.R
import com.localytics.demo.ui.screens.features.profile.setAttributes

@Composable
fun DomainConfiguration(modifier: Modifier, activity: Activity, navController: NavHostController) {

    val messagingHost = remember { mutableStateOf("analytics.localytics.com") }
    val analyticsHost = remember { mutableStateOf("analytics.localytics.com") }
    val manifestHost = remember { mutableStateOf("manifest.localytics.com") }
    val profileHost = remember { mutableStateOf("profile.localytics.com") }
    var ssl by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 16.dp, end = 16.dp, start = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.host),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
        Text(
            text = stringResource(R.string.info_host),
            modifier = Modifier.padding(top = 32.dp),
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        ) {
            OutlinedTextField(
                value = manifestHost.value,
                onValueChange = { manifestHost.value = it },
                label = { Text("Manifest Host", color = Color.LightGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.LightGray
                )
            )
            OutlinedTextField(
                value = analyticsHost.value,
                onValueChange = { analyticsHost.value = it },
                label = { Text("Analytics Host", color = Color.LightGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.LightGray
                )
            )

            OutlinedTextField(
                value = messagingHost.value,
                onValueChange = { messagingHost.value = it },
                label = { Text("Messaging Host", color = Color.LightGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.LightGray
                )
            )
            OutlinedTextField(
                value = profileHost.value,
                onValueChange = { profileHost.value = it },
                label = { Text("Analytics Host", color = Color.LightGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.LightGray
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)

            ) {
                Text(
                    text = "SSL",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .weight(4f)
                )
                Switch(checked = ssl, onCheckedChange = {
                    ssl = it
                }, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = {
                if (profileHost.value.isNotEmpty() && messagingHost.value.isNotEmpty() && analyticsHost.value.isNotEmpty() && manifestHost.value.isNotEmpty()) {
                    setSdkOptions(
                        profileHost.value,
                        messagingHost.value,
                        analyticsHost.value,
                        manifestHost.value,
                        ssl
                    )
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.set))
            }
        }
    }
}

fun setSdkOptions(
    profileHost: String,
    messagingHost: String,
    analyticsHost: String,
    manifestHost: String,
    ssl: Boolean
) {
    val map = HashMap<String, Any>()
    map["ll_manifest_host"] = manifestHost
    map["ll_analytics_host"] = analyticsHost
    map["ll_messaging_host"] = messagingHost
    map["ll_profiles_host"] = profileHost
    map["ll_use_https"] = ssl

    Localytics.setOptions(map as Map<String, Any>)
}
