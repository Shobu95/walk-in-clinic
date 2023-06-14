package com.shobu.walk_in_appointment.domain.use_cases

import com.shobu.walk_in_appointment.data.repository.UserRepository
import com.shobu.walk_in_appointment.domain.models.User
import javax.inject.Inject

class CreateUserUseCase
@Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(user: User) {
        userRepository.createUser(user)
    }

}