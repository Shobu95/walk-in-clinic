package com.shobu.walk_in_clinic.domain.use_cases.authentication

import com.shobu.walk_in_clinic.data.prefs.PreferenceKeys
import com.shobu.walk_in_clinic.data.prefs.UserPreferences
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