package com.app.walk_in_clinic.domain.use_cases

import com.app.walk_in_clinic.data.repository.UserRepository
import com.app.walk_in_clinic.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsersUseCase
@Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> {
        return userRepository.getAll()
    }
}