package ir.amir.isnta.data.repository

import ir.amir.isnta.data.service.ApiResponse
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRepository {

    fun <T> request(
        call: suspend () -> Response<ApiResponse<T>>
    ) = flow<RepositoryResult> {
        emit(RepositoryResult.Loading)
        val response = call()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(RepositoryResult.Data(it.data))
            } ?: emit(RepositoryResult.Error("body is null"))

        } else {
            emit(RepositoryResult.Error(response.message()))
        }
    }
}