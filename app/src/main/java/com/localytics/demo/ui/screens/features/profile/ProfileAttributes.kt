package com.localytics.demo.ui.screens.features.profile

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

@Composable
fun ProfileAttributes(modifier: Modifier, activity: Activity, navController: NavHostController) {
    val profileAttributes = remember { mutableStateOf("") }
    val profileValue = remember { mutableStateOf("") }
    var scopeOrg by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 16.dp, end = 16.dp, start = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.profile),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
        Text(
            text = stringResource(R.string.info_profile),
            modifier = Modifier.padding(top = 32.dp),
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        ) {
            OutlinedTextField(value = profileAttributes.value,
                onValueChange = { profileAttributes.value = it },
                label = { Text("Profile Attribute", color = Color.LightGray) },
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
            OutlinedTextField(value = profileValue.value,
                onValueChange = { profileValue.value = it },
                label = { Text("Profile Value", color = Color.LightGray) },
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
                    text = "Scope 'Org' ('App' by default) ",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .weight(4f)
                )
                Switch(checked = scopeOrg, onCheckedChange = {
                    scopeOrg = it
                }, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = {
                setAttributes(
                    scopeOrg,
                    profileAttributes.value,
                    profileValue.value
                )
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.set))
            }
        }
    }
}

fun setAttributes(scopeOrg: Boolean, attr: String, value: String) {
    if (scopeOrg) {
        Localytics.addProfileAttributesToSet(
            attr,
            arrayOf(value),
            Localytics.ProfileScope.ORGANIZATION
        )
    } else {
        Localytics.addProfileAttributesToSet(
            attr,
            arrayOf(value),
            Localytics.ProfileScope.APPLICATION
        )
    }

}
