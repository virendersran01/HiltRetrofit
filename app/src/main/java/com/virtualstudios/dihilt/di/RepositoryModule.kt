package com.virtualstudios.dihilt.di

import com.virtualstudios.dihilt.data.local.AppUserPreference
import com.virtualstudios.dihilt.data.repository.Repository
import com.virtualstudios.dihilt.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepository(
        repositoryImpl: RepositoryImpl
    ): Repository

    /*@Provides
    @Singleton
    fun provideRepository(
        appUserPreference: AppUserPreference
    ) = RepositoryImpl(appUserPreference)*/
}