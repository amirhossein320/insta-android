package ir.amir.isnta.data.repository

import android.util.Log
import ir.amir.isnta.data.service.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AuthenticationRepository @Inject constructor(private val api: ApiService) : BaseRepository {

    suspend fun signup(
        phoneNumber: String? = null,
        email: String? = null
    ) =
        flow<Boolean> {
            Log.e("TAG", "signup: " )
            api.signup(phoneNumber, email)

        }

}