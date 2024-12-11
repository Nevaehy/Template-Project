package com.heaven.templateproject.di

import com.heaven.templateproject.data.datasources.remote.TestRemoteDataSource
import com.heaven.templateproject.data.datasources.remote.TestRemoteDataSourceImpl
import com.heaven.templateproject.data.repositories.TestRepository
import com.heaven.templateproject.data.repositories.TestRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun provideTestRepository(impl: TestRepositoryImpl): TestRepository

    @Binds
    fun provideTestRemoteDataSource(impl: TestRemoteDataSourceImpl): TestRemoteDataSource
}