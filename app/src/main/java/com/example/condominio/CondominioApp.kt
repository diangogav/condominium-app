package com.example.condominio

import android.app.Application

import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CondominioApp : Application() {
    
    override fun onCreate() {
        super.onCreate()
    }
}
