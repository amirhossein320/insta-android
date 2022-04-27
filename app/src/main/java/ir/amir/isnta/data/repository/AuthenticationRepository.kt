package ir.amir.isnta.data.repository

import ir.amir.isnta.data.service.auth.AuthenticationApiService
import javax.inject.Inject

class AuthenticationRepository
@Inject constructor(private val authApi: AuthenticationApiService) : BaseRepository() {

    suspend fun login(
        username: String, password: String
    ) = requestApi { authApi.login(username, password) }

    suspend fun signup(
        phoneNumber: String? = null, email: String? = null
    ) = requestApi { authApi.signup(phoneNumber, email) }


}