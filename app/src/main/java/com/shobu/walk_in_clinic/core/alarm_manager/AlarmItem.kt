package com.shobu.walk_in_clinic.core.alarm_manager

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val message: String
)
