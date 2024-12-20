package com.localytics.demo

import android.app.Application
import com.localytics.androidx.Localytics

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Localytics.autoIntegrate(this)
        Localytics.setLoggingEnabled(true)
        Localytics.setLocationMonitoringEnabled(true)
    }

}