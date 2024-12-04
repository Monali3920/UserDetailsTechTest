package com.test.assignment.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_detail")
data class UserDataModel(
    @PrimaryKey var id : Long = 0,
    var emailId : String = "",
    var password: String = "",
    var age: Int = 0) {

}