package com.localytics.demo.ui.screens.features.keys

import android.Manifest
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.localytics.androidx.Localytics
import com.localytics.demo.R
import com.localytics.demo.ui.screens.home.PushFeatureUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun AppKeysScreen(modifier: Modifier, activity: Activity, navController: NavHostController) {
    val installId = remember {
        mutableStateOf("")
    }

    val pushToken = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = Unit) {
        pushToken.value = withContext(Dispatchers.IO){
            Localytics.getPushRegistrationId()
        }

        installId.value = withContext(Dispatchers.IO) {
            Localytics.getInstallId()
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 16.dp, end = 16.dp, start = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = stringResource(R.string.total_keys),
            modifier = Modifier.padding(top = 32.dp),
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )

        Card(elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()) {
            Text(
                text = stringResource(R.string.install_id),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )

            Text(
                text = installId.value,
                modifier = Modifier.padding(16.dp)
            )
        }


        Card(elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()) {
            Text(
                text = stringResource(R.string.app_key),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )

            Text(
                text = stringResource(id = R.string.ll_app_key),
                modifier = Modifier.padding(16.dp)
            )
        }

        Card(elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()) {
            Text(
                text = stringResource(R.string.push_token),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )

            Text(
                text = pushToken.value,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}