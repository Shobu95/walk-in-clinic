package com.app.walk_in_clinic.domain.use_cases

import android.util.Log

object TimeSlotsManager {

    private const val location1 =
        "Carbon Health Urgent Care Culver City - West Culver, Washington Boulevard, Culver City, CA, USA"
    private const val location2 = "Urgent Care on Wheels, Santa Monica, Santa Monica, CA, USA"
    private const val location3 =
        "Dr. Paul's Immediate Care, Artesia Boulevard, Redondo Beach, CA, USA"
    private const val location4 =
        "Alhambra Hospital Medical Center, South Raymond Avenue, Alhambra, CA, USA"
    private const val location5 =
        "Children's Hospital Los Angeles, Sunset Boulevard, Los Angeles, CA, USA"
    private const val location6 =
        "Venice Family Clinic - Simms/Mann Health Ctr, Pico Boulevard, Santa Monica, CA, USA"
    private const val location7 =
        "Northeast Community Clinic, East Anaheim Street, Wilmington, CA, USA"
    private const val location8 = "Laguna Beach Community Clinic, 3rd Street, Laguna Beach, CA, USA"
    private const val location9 =
        "Wilmington Community Clinic, North Avalon Boulevard, Wilmington, CA, USA"
    private const val location10 = "Hurtt Family Health Clinic, Newport Avenue, Tustin, CA, USA"

    fun getTimeSlots(locationName: String): List<String> {

        when (locationName) {
            location1 -> {
                return listOf(
                    "01:00 pm",
                    "02:00 pm",
                    "03:00 pm",
                    "04:00 pm",
                    "05:00 pm",
                    "06:00 pm",
                    "07:00 pm",
                    "08:00 pm",
                    "09:00 pm"
                )
            }

            location2 -> {
                return listOf(
                    "08:30 am",
                    "09:30 am",
                    "10:30 am",
                    "11:30 am",
                    "12:30 pm",
                    "01:30 pm",
                    "02:30 pm",
                    "03:30 pm",
                    "04:30 pm",
                    "05:30 pm",
                    "06:30 pm",
                    "07:30 pm",
                )
            }

            location3 -> {
                return listOf(
                    "09:00 am",
                    "10:00 am",
                    "11:00 am",
                    "12:00 pm",
                    "01:00 pm",
                    "02:00 pm",
                    "03:00 pm",
                    "04:00 pm",
                    "05:00 pm",
                    "06:00 pm",
                    "07:00 pm",
                )
            }

            location4 -> {
                Log.v("time_slots", "true")
                return listOf(
                    "09:00 am",
                    "10:00 am",
                    "11:00 am",
                    "12:00 pm",
                    "01:00 pm",
                    "02:00 pm",
                    "03:00 pm",
                    "04:00 pm",
                    "05:00 pm",
                )
            }

            location5 -> {
                return listOf(
                    "10:00 am",
                    "11:00 am",
                    "12:00 pm",
                    "01:00 pm",
                    "02:00 pm",
                    "03:00 pm",
                    "04:00 pm",
                    "05:00 pm",
                    "06:00 pm",
                    "07:00 pm",
                )
            }

            location6 -> {
                return listOf(
                    "08:00 am",
                    "09:00 am",
                    "10:00 am",
                    "11:00 am",
                    "12:00 pm",
                    "01:00 pm",
                    "02:00 pm",
                    "03:00 pm",
                    "04:00 pm",
                    "05:00 pm",
                    "06:00 pm",
                )
            }

            location7 -> {
                return listOf(
                    "08:00 am",
                    "09:00 am",
                    "10:00 am",
                    "11:00 am",
                    "12:00 pm",
                    "01:00 pm",
                    "02:00 pm",
                    "03:00 pm",
                    "04:00 pm",
                    "05:00 pm",
                )
            }

            location8 -> {
                return listOf(
                    "10:00 am",
                    "11:00 am",
                    "12:00 pm",
                    "01:00 pm",
                    "02:00 pm",
                    "03:00 pm",
                    "04:00 pm",
                    "05:00 pm",
                    "06:00 pm",
                )
            }

            location9 -> {
                return listOf(
                    "08:00 am",
                    "09:00 am",
                    "10:00 am",
                    "11:00 am",
                    "12:00 pm",
                    "01:00 pm",
                    "02:00 pm",
                    "03:00 pm",
                    "04:00 pm",
                    "05:00 pm",
                    "06:00 pm",
                    "07:00 pm",
                )
            }

            location10 -> {
                return listOf(
                    "09:00 am",
                    "10:00 am",
                    "11:00 am",
                    "12:00 pm",
                    "01:00 pm",
                    "02:00 pm",
                    "03:00 pm",
                    "04:00 pm",
                    "05:00 pm",
                )
            }

            else -> {
                return listOf(
                    "08:30 am",
                    "09:30 am",
                    "10:30 am",
                    "11:30 am",
                    "12:30 pm",
                    "02:00 pm",
                    "03:00 pm"
                )
            }
        }


    }
}