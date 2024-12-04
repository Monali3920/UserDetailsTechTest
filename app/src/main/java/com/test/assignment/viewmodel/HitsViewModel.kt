package com.test.assignment.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.test.assignment.domain.entities.UserHitsDetaileResponse
import com.test.assignment.domain.usecase.HitDetailsUseCase
import com.test.assignment.utils.NetworkHelper
import com.test.assignment.utils.Resource
import com.test.assignment.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HitsViewModel @Inject constructor(
    private val hitDetailsUseCase: HitDetailsUseCase,
    private val networkHelper: NetworkHelper
): BaseViewModel() {

    private val _hitsDataList = MutableStateFlow<Resource<UserHitsDetaileResponse>>(Resource.Loading)
    val hitDataList: StateFlow<Resource<UserHitsDetaileResponse>> = _hitsDataList


    fun fetchArticleList() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                Log.i("Network is connected", ">>> ")
                _hitsDataList.value = Resource.Loading
                hitDetailsUseCase().onStart {
                    _hitsDataList.value = Resource.Loading
                    Log.i("Inside loading", ">>> ")
                }.catch { e ->
                    Log.i("Inside error", ">>> "+ e.message)
                    _hitsDataList.value = Resource.Error(e.toString())
                }.collect {
                    Log.i("Inside collect", ">>> ")
                    if (it is Resource.Success) {
                        Log.i("Inside collect 2", ""+ Resource.Success(it.data))
                        _hitsDataList.value =
                            Resource.Success(it.data)
                    }
                }
            } else {
                _hitsDataList.value = Resource.Error("NETWORK_ERROR")
            }
        }
    }

}