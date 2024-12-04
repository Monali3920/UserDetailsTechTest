package com.test.assignment.domain.usecase

import com.test.assignment.di.IoDispatcher
import com.test.assignment.domain.repo.HitsDataRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HitDetailsUseCase @Inject constructor(
    private val hitsRepository: HitsDataRepository,
    @IoDispatcher private val ioDispatcher: CoroutineContext
) {
    suspend operator fun invoke() = flow {
        emit(hitsRepository.getHitsList())
    }.flowOn(ioDispatcher) // This one takes precedence
}