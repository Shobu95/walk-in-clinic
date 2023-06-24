package com.shobu.walk_in_appointment.domain.use_cases

import com.shobu.walk_in_appointment.data.prefs.PreferenceKeys
import com.shobu.walk_in_appointment.data.prefs.UserPreferences
import com.shobu.walk_in_appointment.domain.models.User
import javax.inject.Inject

class GetSessionUseCase
@Inject constructor(private val prefs: UserPreferences) {
    operator fun invoke(): User {
        return User(
            id = prefs.getInt(PreferenceKeys.USER_ID, 0),
            fullName = prefs.getString(PreferenceKeys.FULL_NAME, ""),
            phoneNumber = prefs.getString(PreferenceKeys.PHONE_NUMBER, ""),
            gender = prefs.getString(PreferenceKeys.GENDER, ""),
            dateOfBirth = prefs.getString(PreferenceKeys.DATE_OF_BIRTH, ""),
            email = prefs.getString(PreferenceKeys.EMAIL, ""),
            password = "",
        )
    }
}