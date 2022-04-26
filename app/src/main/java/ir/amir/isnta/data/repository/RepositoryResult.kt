package ir.amir.isnta.data.repository

sealed class RepositoryResult {

    object Loading : RepositoryResult()

    data class Error(val message: String) : RepositoryResult()

    data class Data<T>(val data: T) : RepositoryResult()
}
