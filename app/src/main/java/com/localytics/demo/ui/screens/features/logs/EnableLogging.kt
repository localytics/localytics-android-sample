package com.localytics.demo.ui.screens.features.logs

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.localytics.androidx.Localytics
import com.localytics.demo.R

@Composable
fun EnableLogging(modifier: Modifier, activity: Activity, navController: NavHostController) {
    var checked by remember { mutableStateOf(Localytics.isLoggingEnabled()) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 16.dp, end = 16.dp, start = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = stringResource(R.string.logs),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
        Text(
            text = stringResource(R.string.info_logs),
            modifier = Modifier.padding(top = 32.dp),
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)

        ) {
            Text(
                text = stringResource(R.string.enable),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(4f)
            )
            Switch(checked = checked, onCheckedChange = {
                checked = it
            }, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = { enableLogging(checked) }, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.submit))
        }
    }
}

fun enableLogging(enable: Boolean) {
    Localytics.setLoggingEnabled(enable)
}
