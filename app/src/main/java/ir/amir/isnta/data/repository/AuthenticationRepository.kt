package ir.amir.isnta.data.repository

import ir.amir.isnta.data.service.auth.AuthenticationApiService
import ir.amir.isnta.data.service.auth.UserResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationRepository
@Inject constructor(private val authApi: AuthenticationApiService) : BaseRepository() {

    suspend fun login(
        username: String, password: String
    ) = request { authApi.login(username, password) }

    suspend fun signup(
        phoneNumber: String? = null, email: String? = null
    ) = request { authApi.signup(phoneNumber, email) }


}