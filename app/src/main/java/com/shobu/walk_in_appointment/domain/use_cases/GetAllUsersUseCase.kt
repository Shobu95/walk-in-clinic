package com.shobu.walk_in_appointment.domain.use_cases

import com.shobu.walk_in_appointment.data.repository.UserRepository
import com.shobu.walk_in_appointment.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsersUseCase
@Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(): Flow<List<User>> {
        return userRepository.getAll()
    }
}