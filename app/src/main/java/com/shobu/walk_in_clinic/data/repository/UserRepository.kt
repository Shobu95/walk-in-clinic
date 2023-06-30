package com.shobu.walk_in_clinic.data.repository

import com.shobu.walk_in_clinic.data.local.user.UserDao
import com.shobu.walk_in_clinic.data.local.user.UserEntity
import com.shobu.walk_in_clinic.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository
@Inject constructor(
    private val userDao: UserDao
) {
    fun getAll(): Flow<List<User>> {
        return userDao.getAllUsers().map { it ->
            it.asDomainModelList()
        }
    }

    fun createUser(user: User) {
        userDao.createUser(user.asEntityModel())
    }

}

fun User.asEntityModel(): UserEntity {
    return UserEntity(
        id = this.id,
        fullName = this.fullName,
        phoneNumber = this.phoneNumber,
        dateOfBirth = this.dateOfBirth,
        gender = this.gender,
        height = this.height,
        weight = this.weight,
        email = this.email,
        password = this.password,
    )
}

fun List<UserEntity>.asDomainModelList(): List<User> {
    return map {
        User(
            id = it.id,
            fullName = it.fullName,
            phoneNumber = it.phoneNumber,
            dateOfBirth = it.dateOfBirth,
            gender = it.gender,
            height = it.height,
            weight = it.weight,
            email = it.email,
            password = it.password
        )
    }
}
