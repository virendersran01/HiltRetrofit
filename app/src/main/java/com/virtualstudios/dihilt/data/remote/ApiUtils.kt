package com.virtualstudios.dihilt.data.remote

import android.text.TextUtils
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

//createPartFromString
fun String.toRequestBody(): RequestBody {
    return toRequestBody("text/plain".toMediaTypeOrNull())
}

//prepareFilePart
fun String.toFileBody(keyName: String = "image"): MultipartBody.Part? {
    var body: MultipartBody.Part? = null
    if (!TextUtils.isEmpty(this)) {
        val file = File(this)
        val requestFile =
            file.asRequestBody("image/*".toMediaTypeOrNull())
        body = MultipartBody.Part.createFormData(keyName, file.name, requestFile)
    }
    return body
}

@Serializable
data class ApiResponse<T>(
    @SerialName("status")
    val status: Int = -1,
    @SerialName("message")
    val message: String?,
    @SerialName("result")
    val data: T? = null,
    @SerialName("success")
    val success: Boolean
)

class InvalidAuthorization : Exception()

suspend fun <T> handleApiCall(
    apiCall: suspend () -> ApiResponse<T>
): UiState<T>{
    return try {
        val apiResponse = apiCall()
        when(apiResponse.status){
            200 -> UiState.Success(data = apiResponse.data, message = apiResponse.message)
            401 -> UiState.Error(exception = InvalidAuthorization(), errorMessage = apiResponse.message ?: "Invalid authorization")
            else -> UiState.Error(errorMessage = apiResponse.message ?: "Something went wrong")
        }
    }catch (exception: Exception){
        UiState.Error(
            exception = exception,
            errorMessage = exception.message ?: "Something went wrong"
        )
    }
}

sealed class UiState<out T>{

    data object Init : UiState<Nothing>()

    data object Loading : UiState<Nothing>()

    data class Success<T>(
        val data: T? = null,
        val message: String? = null
    ) : UiState<T>()

    data class Error(
        val exception: Throwable = Exception(),
        val errorMessage: String = ""
    ) : UiState<Nothing>()

    override fun toString(): String {
        return when(this){
            Init -> "Init"
            Loading -> "Loading"
            is Success -> "Success[data=$data, message=$message]"
            is Error -> "Error[exception=$exception, errorMessage=$errorMessage]"
        }
    }
}