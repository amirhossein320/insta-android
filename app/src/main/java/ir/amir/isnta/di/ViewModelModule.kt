package ir.amir.isnta.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ir.amir.isnta.data.service.auth.AuthenticationApiService
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideAuthenticationApiService(retrofit: Retrofit) =
        retrofit.create(AuthenticationApiService::class.java)

}