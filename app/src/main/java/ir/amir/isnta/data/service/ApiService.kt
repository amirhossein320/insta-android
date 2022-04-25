package ir.amir.isnta.data.service

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {


    @POST("signup")
    @FormUrlEncoded
    suspend fun signup(
        @Field("phone_number") phoneNumber: String? = null,
        @Field("email")email: String? = null
    ): Response<ApiResponse<Boolean>>
}