package com.localytics.demo.ui.screens.cutomerid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.localytics.demo.R

@Composable
fun CustomerIdScreen(modifier: Modifier) {
    val customerViewModel = CustomerViewModel();
    customerViewModel.getCustomerId()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Text(
            text = stringResource(R.string.customer_id),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )

        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp), elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
            SetCustomerIdUi(customerViewModel)
        }

        Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
            LogoutCustomerIdUi(customerViewModel)
        }

    }
}




