package com.app.walk_in_clinic.core.alarm_manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import com.app.walk_in_clinic.core.notification_service.AppointmentNotificationService


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        println("Alarm triggered: $message")

        val service = AppointmentNotificationService(context!!)
        service.showNotification(message)
        val pm = (context.getSystemService(Context.POWER_SERVICE) as PowerManager)
        val levelAndFlags = PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP
        val wakeLock = pm.newWakeLock(levelAndFlags, "MyApp::MyWakelockTag")

        wakeLock.acquire()
        wakeLock.release()

    }
}