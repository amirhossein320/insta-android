package ir.amir.isnta.data.service.auth

import ir.amir.isnta.data.service.ApiResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationApiService {

    @POST("signup")
    @FormUrlEncoded
    suspend fun signup(
        @Field("phone_number") phoneNumber: String? = null,
        @Field("email") email: String? = null
    ): Response<ApiResponse<UserResponse>>

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<ApiResponse<UserResponse>>

}