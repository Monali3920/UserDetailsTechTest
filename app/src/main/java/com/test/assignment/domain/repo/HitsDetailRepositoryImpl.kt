package com.test.assignment.domain.repo

import com.test.assignment.data.service.api.ApiService
import com.test.assignment.domain.entities.UserHitsDetaileResponse
import com.test.assignment.utils.Resource

class HitsDetailRepositoryImpl (
    private val apiService: ApiService
) : HitsDataRepository {

    override suspend fun getHitsList(): Resource<UserHitsDetaileResponse> {
        val response = apiService.getUserDetailList().takeIf { it.isSuccessful }
        return response?.body()?.let { resultResponse ->
            Resource.Success(resultResponse)
        } ?: Resource.Error( response?.errorBody().toString())
    }
}