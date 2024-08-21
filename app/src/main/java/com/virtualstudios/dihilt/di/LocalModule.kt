package com.virtualstudios.dihilt.di

import android.content.Context
import android.content.SharedPreferences
import com.virtualstudios.dihilt.data.local.AppUserPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    private const val KEY_SHARED_PREFERENCES_NAME = "app_preferences"

    @Singleton
    @Provides
    fun providesSharedPreference(
        @ApplicationContext context: Context
    ): SharedPreferences = context.getSharedPreferences(
        KEY_SHARED_PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    @Singleton
    @Provides
    fun providesAppSharedPreferences(
        sharedPreferences: SharedPreferences
    ) = AppUserPreference(sharedPreferences)

}