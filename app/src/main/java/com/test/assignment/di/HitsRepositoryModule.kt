package com.test.assignment.di

import com.test.assignment.MyApplication
import com.test.assignment.data.service.api.ApiService
import com.test.assignment.di.AppModule.provideNetworkHelper
import com.test.assignment.domain.repo.HitsDataRepository
import com.test.assignment.domain.repo.HitsDetailRepositoryImpl
import com.test.assignment.domain.usecase.HitDetailsUseCase
import com.test.assignment.viewmodel.HitsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class HitsRepositoryModule {

    @Provides
    fun provideDataRepository(apiService: ApiService): HitsDataRepository {
        return HitsDetailRepositoryImpl(apiService)
    }

    @ViewModelScoped
    @Provides
    fun providesHitsDetailUseCase(repository: HitsDataRepository) : HitDetailsUseCase =
        HitDetailsUseCase(repository, AppModule.provideIoDispatcher())

    @ViewModelScoped
    @Provides
    fun providesHitsViewModel(useCase: HitDetailsUseCase): HitsViewModel =
        HitsViewModel(useCase, provideNetworkHelper(context = MyApplication.instance))
}