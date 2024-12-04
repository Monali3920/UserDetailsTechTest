package com.test.assignment.data

import com.test.assignment.data.db.UserDetailDao
import com.test.assignment.domain.entities.UserDataModel
import com.test.assignment.domain.repo.UserDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDetailRepositoryImpl @Inject constructor(private val dao: UserDetailDao) : UserDetailRepository {

    override suspend fun addUser(userDataModel: UserDataModel) {
        dao.insertUserDetail(userDataModel)
    }

    override suspend fun getAllUsers(): Flow<List<UserDataModel>> {
        return dao.getAllUsers()
    }

    override suspend fun updateUserDetail(userDataModel: UserDataModel) {
        dao.updateUserDetail(userDataModel)
    }

    override suspend fun deleteUser(userDataModel: UserDataModel) {
        dao.deleteUser(userDataModel)
    }

    override suspend fun getUserById(id: String) {
        dao.getUserById(id)
    }

    override suspend fun isValidUser(email: String, password: String): Flow<UserDataModel?> {
        return dao.isValidUser(email, password)
    }
}