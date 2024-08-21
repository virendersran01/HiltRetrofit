package com.virtualstudios.dihilt.data.remote

import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(

    ): ApiResponse<Any>

}