package com.localytics.demo.ui.screens.cutomerid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
fun SetCustomerIdUi(customerViewModel: CustomerViewModel) {
    val customerId = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }

    customerViewModel.customerId.observeForever {
        customerId.value = it
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {

        Text(
            text = stringResource(R.string.customer_id_feature),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        Spacer(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(Color.Gray)
        )

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            value = customerId.value,
            //isError = isError.value,
            supportingText = {
                if (isError.value) {
                    Text(
                        text = stringResource(R.string.this_field_is_required), color = Color.Red
                    )
                }
            },
            onValueChange = {
                isError.value = it.isEmpty()
                customerId.value = it
            },
            label = { Text(stringResource(R.string.enter_customer_id)) })

        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
            if (customerId.value.isNotEmpty()) {
                setCustomerId(customerId.value, customerViewModel)
            } else {
                isError.value = true
            }
        }) {
            Text(
                text = stringResource(R.string.set),
                modifier = Modifier.padding(start = 32.dp, end = 32.dp)
            )
        }
    }
}

fun setCustomerId(cid: String, customerViewModel: CustomerViewModel) {
    customerViewModel.setCustomerId(cid)
}
