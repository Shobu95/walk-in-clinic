package com.app.walk_in_clinic.domain.use_cases.authentication

import com.app.walk_in_clinic.data.repository.UserRepository
import com.app.walk_in_clinic.domain.models.User
import javax.inject.Inject

class CreateUserUseCase
@Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(user: User) {
        userRepository.createUser(user)
    }

}