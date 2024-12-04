package com.test.assignment.data.service.api

import com.test.assignment.BuildConfig.pixabayAPIkey
import com.test.assignment.domain.entities.UserHitsDetaileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // https://pixabay.com/api/?key=47387031-5530a3da419b941cad146aa01&q=yellow+flowers&image_type=photo&pretty=true
    @GET("api/?key=$pixabayAPIkey")
    suspend fun getUserDetailList(@Query("key") key:String = pixabayAPIkey): Response<UserHitsDetaileResponse>

}