package com.localytics.demo.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.localytics.demo.R

@Composable
fun InboxUi(homeViewModel: HomeViewModel) {
    val inboxCount = remember {
        mutableIntStateOf(10)
    }

    homeViewModel.inboxCount.observeForever {
        inboxCount.intValue = it
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "# Inbox Feature #",
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.Gray))
        
        Text(text = stringResource(R.string.inbox_count, inboxCount.value), modifier = Modifier
            .padding(16.dp)
            .align(alignment = androidx.compose.ui.Alignment.CenterHorizontally))
    }
}