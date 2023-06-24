package com.shobu.walk_in_appointment.domain.use_cases

import com.shobu.walk_in_appointment.data.prefs.PreferenceKeys
import com.shobu.walk_in_appointment.data.prefs.UserPreferences
import com.shobu.walk_in_appointment.domain.models.User
import javax.inject.Inject

class SaveUserSessionUseCase
@Inject constructor(private val prefs: UserPreferences) {

    operator fun invoke(user: User) {
        prefs.setInt(PreferenceKeys.USER_ID, user.id ?: 0)
        prefs.setString(PreferenceKeys.FULL_NAME, user.fullName)
        prefs.setString(PreferenceKeys.PHONE_NUMBER, user.phoneNumber)
        prefs.setString(PreferenceKeys.EMAIL, user.email)
        prefs.setString(PreferenceKeys.GENDER, user.gender)
        prefs.setString(PreferenceKeys.DATE_OF_BIRTH, user.dateOfBirth)
    }
}