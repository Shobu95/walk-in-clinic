package com.shobu.walk_in_appointment.core.di

import com.shobu.walk_in_appointment.data.repository.UserRepository
import com.shobu.walk_in_appointment.domain.use_cases.CreateUserUseCase
import com.shobu.walk_in_appointment.domain.use_cases.GetAllUsersUseCase
import com.shobu.walk_in_appointment.domain.use_cases.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesCreateUserUseCase(userRepository: UserRepository): CreateUserUseCase {
        return CreateUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun providesGetAllUsersUseCase(userRepository: UserRepository): GetAllUsersUseCase {
        return GetAllUsersUseCase(userRepository)
    }


    @Provides
    @Singleton
    fun providesLoginUseCase(getAllUsersUseCase: GetAllUsersUseCase): LoginUseCase {
        return LoginUseCase(getAllUsersUseCase)
    }


}