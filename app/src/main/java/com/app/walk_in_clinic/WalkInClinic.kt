package com.app.walk_in_clinic

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.app.walk_in_clinic.core.notification_service.AppointmentNotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WalkInClinic : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                AppointmentNotificationService.APPOINTMENT_CHANNEL_ID,
                "Appointment",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "User for displaying appointment notifications"

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
