package com.localytics.demo.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.localytics.androidx.Localytics
import com.localytics.demo.R

@Composable
fun PushFeatureUi(hasPermission: Boolean, homeViewModel: HomeViewModel) {

    val pushToken = remember {
        mutableStateOf("")
    }
    // Register for push notifications
    if (hasPermission) {
        Localytics.registerPush()
        homeViewModel.getPushToken()
        homeViewModel.token.observeForever {
            if (it != null) {
                pushToken.value = it
            }
        }
    }
    

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.push_notification_feature),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
        Spacer(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(Color.Gray)
        )

        Text(
            text = stringResource(R.string.push_info),
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(checked = hasPermission, onCheckedChange = { })
            Text(
                stringResource(R.string.ready_to_receive)
            )
        }
        
        if (hasPermission) {
            Text(
                text = pushToken.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(16.dp)
                    .border(1.dp, Color.Gray, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                    .padding(16.dp))
        }
    }
}