package com.localytics.demo.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.localytics.androidx.Localytics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _inboxCount = MutableLiveData<Int>()
    val inboxCount: LiveData<Int> = _inboxCount

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _inboxCount.postValue(Localytics.getDisplayableInboxCampaigns().size)
        }
    }


    fun logEvent(name: String, params: Map<String, String>) {
        if (params.isNotEmpty()) {
            Localytics.tagEvent(name, params)
        } else {
            Localytics.tagEvent(name)
        }
    }

    fun logEvent(name: String) {
        Localytics.tagEvent(name)
    }

    fun getPushToken() {
        viewModelScope.launch(Dispatchers.IO) {
            _token.postValue(Localytics.getPushRegistrationId())
        }
    }
}