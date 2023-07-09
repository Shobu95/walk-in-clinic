package com.shobu.walk_in_clinic.core.alarm_manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.shobu.walk_in_clinic.core.notification_service.AppointmentNotificationService

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        println("Alarm triggered: $message")

        val service = AppointmentNotificationService(context!!)
        service.showNotification(message)
    }
}