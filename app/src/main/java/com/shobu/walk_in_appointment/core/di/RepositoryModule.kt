package com.shobu.walk_in_appointment.core.di

import com.shobu.walk_in_appointment.data.local.user.UserDao
import com.shobu.walk_in_appointment.data.repository.UserRepository
import com.shobu.walk_in_appointment.domain.use_cases.CreateUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

}