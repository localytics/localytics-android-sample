package com.localytics.demo.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.localytics.demo.R

@Composable
fun InAppUi(homeViewModel: HomeViewModel) {
    val eventName = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.in_app_message_feature),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(Color.Gray)
        )

        Text(
            text = stringResource(R.string.in_app_info),
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )

        OutlinedTextField(
            value = eventName.value,
            onValueChange = { eventName.value = it },
            label = { Text(stringResource(R.string.enter_event_name)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Yellow, unfocusedBorderColor = Color.Gray
            )
        )

        Button(
            onClick = { if(eventName.value.isNotEmpty())
            {
                logEvent(eventName.value,homeViewModel)
            }},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
        ) {
            Text(text = stringResource(R.string.submit))
        }
    }
}

fun logEvent(name: String, viewModel: HomeViewModel) {
    viewModel.logEvent(name)
}


