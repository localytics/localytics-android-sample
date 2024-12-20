package com.localytics.demo.ui.screens.cutomerid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun LogoutCustomerIdUi(customerViewModel: CustomerViewModel) {
    val customerId = remember { mutableStateOf("") }

    customerViewModel.customerId.observeForever {
        customerId.value = it
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = stringResource(R.string.logout_customer_id),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.Gray)
        )

        Text(
            text = "Current Customer Id : ${customerId.value}",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Button(
            onClick = { logoutCustomer(customerViewModel) },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.logout),
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }

    }
}

fun logoutCustomer(customerViewModel: CustomerViewModel) {
    customerViewModel.setCustomerId(null)
}
