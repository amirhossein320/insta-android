package ir.amir.isnta.data.service

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import ir.amir.isnta.BuildConfig
import ir.amir.isnta.R
import ir.amir.isnta.util.hasNetwork
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

class InitializeRetrofit {


    fun retrofit(context: Context) = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/api/")
        .client(okHttp(context))
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    fun okHttp(context: Context): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .addInterceptor(networkInterceptor(context))
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .addInterceptor(networkInterceptor(context))
                .build()

        }
    }

    private fun networkInterceptor(context: Context): Interceptor = Interceptor {

        val originalRequest = it.request()
        try {
            if (context.hasNetwork()) {
                it.proceed(originalRequest)
            } else {
                throw NoConnectivityException(context.getString(R.string.err_no_internet))
            }
        } catch (e: SocketTimeoutException) {
            throw SocketTimeoutException(context.getString(R.string.err_time_out))
        } catch (e: Exception) {
            throw IOException(context.getString(R.string.err_request_failed))
        }

    }

}

class NoConnectivityException(message: String) : IOException(message)