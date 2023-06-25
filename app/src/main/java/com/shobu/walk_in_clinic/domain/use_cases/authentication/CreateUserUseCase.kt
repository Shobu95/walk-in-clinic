package com.shobu.walk_in_clinic.domain.use_cases.authentication

import com.shobu.walk_in_clinic.data.repository.UserRepository
import com.shobu.walk_in_clinic.domain.models.User
import javax.inject.Inject

class CreateUserUseCase
@Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(user: User) {
        userRepository.createUser(user)
    }

}