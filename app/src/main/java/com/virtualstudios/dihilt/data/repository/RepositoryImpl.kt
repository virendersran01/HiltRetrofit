package com.virtualstudios.dihilt.data.repository

import com.virtualstudios.dihilt.data.local.AppUserPreference
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val appUserPreference: AppUserPreference
) : Repository {

    override fun login(username: String, password: String): Boolean {
        appUserPreference.saveName(username)
        return true
    }
}