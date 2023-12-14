package com.d121211078.valorantkuy

import android.app.Application
import com.d121211078.valorantkuy.data.AppContainer
import com.d121211078.valorantkuy.data.DefaultAppContainer

class ValorantKuy: Application() {
    lateinit var  container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}