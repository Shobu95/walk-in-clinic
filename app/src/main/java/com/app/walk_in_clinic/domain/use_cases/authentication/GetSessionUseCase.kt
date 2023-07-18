package com.app.walk_in_clinic.domain.use_cases.authentication

import com.app.walk_in_clinic.data.prefs.PreferenceKeys
import com.app.walk_in_clinic.data.prefs.UserPreferences
import com.app.walk_in_clinic.domain.models.User
import javax.inject.Inject

class GetSessionUseCase
@Inject constructor(private val prefs: UserPreferences) {
    operator fun invoke(): User {
        return User(
            id = prefs.getInt(PreferenceKeys.USER_ID, 0),
            fullName = prefs.getString(PreferenceKeys.FULL_NAME, ""),
            phoneNumber = prefs.getString(PreferenceKeys.PHONE_NUMBER, ""),
            gender = prefs.getString(PreferenceKeys.GENDER, ""),
            height = prefs.getString(PreferenceKeys.HEIGHT, ""),
            weight = prefs.getString(PreferenceKeys.WEIGHT, ""),
            dateOfBirth = prefs.getString(PreferenceKeys.DATE_OF_BIRTH, ""),
            email = prefs.getString(PreferenceKeys.EMAIL, ""),
            password = "",
        )
    }
}