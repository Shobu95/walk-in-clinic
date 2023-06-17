package com.shobu.walk_in_appointment.domain.use_cases

import com.shobu.walk_in_appointment.domain.models.User
import javax.inject.Inject

class ValidateUserUseCase
@Inject constructor() {

    operator fun invoke(user: User): Pair<Boolean, String> {

        if(user.fullName.isNullOrEmpty()){
            return Pair(false, "Please enter full name")
        }

        if (user.dateOfBirth.isNullOrEmpty()) {
            return Pair(false, "Please select date of birth")
        }

        if(user.gender.isNullOrEmpty()) {
            return Pair(false, "Please select gender")
        }

        if (user.email.isNullOrEmpty()) {
            return Pair(false, "Please enter valid email")
        }

        if (user.password.isNullOrEmpty()) {
            return Pair(false, "Please enter password")
        }

        return Pair(true, "")


    }
}