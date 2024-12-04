package com.test.assignment.domain.repo

import com.test.assignment.domain.entities.UserHitsDetaileResponse
import com.test.assignment.utils.Resource

interface HitsDataRepository{
    suspend fun getHitsList(): Resource<UserHitsDetaileResponse>
}