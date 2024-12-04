package com.test.assignment.domain.repo

import com.test.assignment.domain.entities.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {

    suspend fun addUser(userDataModel: UserDataModel)

    suspend fun getAllUsers(): Flow<List<UserDataModel>>

    suspend fun updateUserDetail(userDataModel: UserDataModel)

    suspend fun deleteUser(userDataModel: UserDataModel)

    suspend fun getUserById(id : String)

    suspend fun isValidUser(email : String, password: String) : Flow<UserDataModel?>
}