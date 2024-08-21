package com.virtualstudios.dihilt.data.repository

interface Repository {

    fun login(username: String, password: String): Boolean

}