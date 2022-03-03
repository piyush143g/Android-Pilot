package com.servicefinder.local.application

import android.app.Application
import com.servicefinder.local.network.ApiBuilder
import com.servicefinder.local.network.ApiServiceFactory

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeFirebase()
        initializeApiBuilder()
    }

    private fun initializeApiBuilder(){
        ApiBuilder.initialize(ApiServiceFactory.getInstance("hostname", this))
    }

    private fun initializeFirebase(){

    }
}