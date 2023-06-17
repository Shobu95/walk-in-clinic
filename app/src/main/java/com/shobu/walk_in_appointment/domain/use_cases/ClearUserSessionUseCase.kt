package com.shobu.walk_in_appointment.domain.use_cases

import com.shobu.walk_in_appointment.data.prefs.PreferenceKeys
import com.shobu.walk_in_appointment.data.prefs.UserPreferences
import javax.inject.Inject

class ClearUserSessionUseCase
@Inject constructor(private val prefs: UserPreferences) {

    operator fun invoke() {
        prefs.setString(PreferenceKeys.FULL_NAME, "")
        prefs.setString(PreferenceKeys.EMAIL, "")
        prefs.setString(PreferenceKeys.GENDER, "")
        prefs.setString(PreferenceKeys.DATE_OF_BIRTH, "")
        prefs.setInt(PreferenceKeys.USER_ID, 0)
    }
}