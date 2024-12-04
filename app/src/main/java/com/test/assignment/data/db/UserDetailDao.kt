package com.test.assignment.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.test.assignment.domain.entities.UserDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetail(userDetail : UserDataModel)

    @Update
    suspend fun updateUserDetail(userDetail: UserDataModel)

    @Query("select * from `user_detail`")
    fun getAllUsers() : Flow<List<UserDataModel>>

    @Query("update `user_detail` set emailId=:userEmail")
    suspend fun updateUserEmail(userEmail : String)

    @Query("select * from `user_detail` where id=:emailId")
    suspend fun getUserById(emailId : String) : UserDataModel?

    @Delete
    suspend fun deleteUser(userDetail: UserDataModel)

    @Query("select * from `user_detail` where emailId = :email AND password = :password LIMIT 1")
    fun isValidUser(email: String, password: String): Flow<UserDataModel?>
}