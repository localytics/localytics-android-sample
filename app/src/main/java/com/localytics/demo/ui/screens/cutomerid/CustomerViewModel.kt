package com.localytics.demo.ui.screens.cutomerid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.localytics.androidx.Localytics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel : ViewModel() {

    private val _customerId = MutableLiveData<String>()
    val customerId: LiveData<String> = _customerId

    fun setCustomerId(id: String?) {
        Localytics.setCustomerId(id).also { getCustomerId() }
    }

    fun getCustomerId() {
        viewModelScope.launch(Dispatchers.IO) {
            _customerId.postValue(Localytics.getCustomerId())
        }
    }

}