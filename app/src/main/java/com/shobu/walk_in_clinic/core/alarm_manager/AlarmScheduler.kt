package com.shobu.walk_in_clinic.core.alarm_manager

interface AlarmScheduler {
    fun schedule(item: AlarmItem)
    fun cancel(item: AlarmItem)
}