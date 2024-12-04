package com.test.assignment.domain.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.assignment.domain.usecase.UserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val useCase: UserDetailUseCase) : ViewModel() {

    private var _userList = MutableLiveData<List<UserDataModel>>(emptyList())
    var userList: LiveData<List<UserDataModel>> = _userList

    init {
        getUserList()
    }


    val userLiveData = MutableLiveData<UserDataModel>()

    fun addUser(userDataModel: UserDataModel) {
        viewModelScope.launch {
            useCase.addUser(userDataModel)
        }
    }

    fun isValidUser(emailId: String, password: String){
        viewModelScope.launch {
            useCase.isValidUser(emailId, password).collect{
                userLiveData.postValue(it)
            }
        }
    }

    private fun getUserList() {
        viewModelScope.launch {
            useCase.getUserList().collect {
                // it will show recently added item on top
                if (it.isNotEmpty()) {
                    if (it.size > 1) _userList.value = it.reversed()
                    else _userList.value = it
                }else _userList.value = emptyList()
            }
        }
    }

}