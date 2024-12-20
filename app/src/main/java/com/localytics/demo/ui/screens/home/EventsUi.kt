package com.localytics.demo.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.localytics.demo.R

@Composable
fun EventsUi(homeViewModel: HomeViewModel) {
    val eventName = remember { mutableStateOf("") }
    val key1 = remember { mutableStateOf("") }
    val value1 = remember { mutableStateOf("") }

    val key2 = remember { mutableStateOf("") }
    val value2 = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "# Events Feature #",
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )

        OutlinedTextField(value = eventName.value,
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


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        ) {
            OutlinedTextField(value = key1.value,
                onValueChange = { key1.value = it },
                label = { Text("key") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, end = 4.dp) // Occupies 50% width
            )
            OutlinedTextField(value = value1.value,
                onValueChange = { value1.value = it },
                label = { Text("Value") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp, end = 8.dp) // Occupies 50% width
            )
            Spacer(modifier = Modifier.weight(.05f))
            Icon(painter = painterResource(id = R.drawable.baseline_cancel_24),
                contentDescription = "Cancel Icon",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {
                        key1.value = ""
                        value1.value = ""
                    }
                    .height(32.dp)
                    .width(32.dp)

            )

            Spacer(modifier = Modifier.weight(.05f))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        ) {
            OutlinedTextField(value = key2.value,
                onValueChange = { key2.value = it },
                label = { Text("key") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, end = 4.dp) // Occupies 50% width
            )
            OutlinedTextField(value = value2.value,
                onValueChange = { value2.value = it },
                label = { Text("Value") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp, end = 8.dp) // Occupies 50% width
            )
            Spacer(modifier = Modifier.weight(.05f))

            Icon(painter = painterResource(id = R.drawable.baseline_cancel_24),
                contentDescription = "Cancel Icon",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {
                        key2.value = ""
                        value2.value = ""
                    }
                    .height(32.dp)
                    .width(32.dp)

            )

            Spacer(modifier = Modifier.weight(.05f))

        }


        Button(
            onClick = {
                logEvent(
                    eventName.value,
                    key1.value,
                    value1.value,
                    key2.value,
                    value2.value,
                    homeViewModel
                ) {
                    eventName.value = ""
                    key1.value = ""
                    value1.value = ""
                    key2.value = ""
                    value2.value = ""
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
        ) {
            Text(text = stringResource(R.string.tag_event))
        }
    }
}

fun logEvent(
    name: String,
    key1: String,
    value1: String,
    key2: String,
    value2: String,
    homeViewModel: HomeViewModel,
    success: () -> Unit
) {
    if (name.isEmpty()) {
        return
    }
    val attributes: HashMap<String, String> = HashMap()
    if (key1.isNotEmpty() && value1.isNotEmpty()) {
        attributes[key1] = value1
    }
    if (key2.isNotEmpty() && value2.isNotEmpty()) {
        attributes[key2] = value2
    }
    homeViewModel.logEvent(name, attributes)
    success.invoke()
}
