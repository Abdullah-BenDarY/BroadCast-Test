package com.example.broadcasttest.broadcast

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.broadcasttest.CHANNEL

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    // crate notification channel for api 26 and above ( android 8.0)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL,
                "My Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "App Name"
            val nfm = getSystemService(NotificationManager::class.java)
            nfm.createNotificationChannel(channel)
        }
    }
}