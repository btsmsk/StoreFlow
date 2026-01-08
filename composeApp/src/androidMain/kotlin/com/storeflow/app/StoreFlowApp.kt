package com.storeflow.app

import android.app.Application
import com.storeflow.app.di.initKoin

class StoreFlowApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Koin for the whole app
        initKoin()
    }
}
