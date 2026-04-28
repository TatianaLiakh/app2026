package com.example.app2026

import android.app.Application
import com.example.app2026.database.AppContainer
import com.example.app2026.database.DefaultAppContainer

class MovieDBApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}