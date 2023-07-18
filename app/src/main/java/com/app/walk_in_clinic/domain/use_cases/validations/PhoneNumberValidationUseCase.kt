package com.app.walk_in_clinic.domain.use_cases.validations

import javax.inject.Inject

class PhoneNumberValidationUseCase
@Inject constructor() {
    operator fun invoke(phoneNumber: String): Pair<Boolean, String> {

        if (phoneNumber.isEmpty()) {
            return Pair(false, "Please enter phone number")
        }
        return if (phoneNumber.startsWith("+1") && phoneNumber.length == 12) {
            Pair(true, "")
        } else if (phoneNumber.startsWith("+92") && phoneNumber.length == 13) {
            Pair(true, "")
        } else {
            Pair(false, "Please enter a valid phone number")
        }

    }
}