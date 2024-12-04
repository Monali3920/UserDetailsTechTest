package com.test.assignment.domain.usecase

import com.test.assignment.domain.entities.UserDataModel
import com.test.assignment.domain.repo.UserDetailRepository
import kotlinx.coroutines.flow.Flow

class UserDetailUseCase(private val repository: UserDetailRepository) {
    suspend fun addUser(userDataModel: UserDataModel) = repository.addUser(userDataModel)
    suspend fun isValidUser(email: String, pasword: String) = repository.isValidUser(email, pasword)
    suspend fun getUserList(): Flow<List<UserDataModel>> {
        return repository.getAllUsers()
    }
}