package com.example.guessdice

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "ffe3c881-8ae0-4887-81a9-499082f58bb7"

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}