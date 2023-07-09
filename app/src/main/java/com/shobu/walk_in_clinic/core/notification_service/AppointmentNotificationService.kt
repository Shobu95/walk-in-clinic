package com.shobu.walk_in_clinic.core.notification_service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.walk_in_appointment.ui.splash.SplashActivity
import com.shobu.walk_in_clinic.R
import dagger.hilt.android.qualifiers.ApplicationContext

class AppointmentNotificationService(
    @ApplicationContext
    private val context: Context
) {

    companion object {
        const val APPOINTMENT_CHANNEL_ID = "appointment_channel"
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    fun showNotification(message: String) {

        val activityIntent = Intent(context, SplashActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val notification = NotificationCompat.Builder(context, APPOINTMENT_CHANNEL_ID)
            .setSmallIcon(R.drawable.app_logo)
            .setContentTitle("Appointment Alert")
            .setContentText(message)
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(1, notification)

    }
}