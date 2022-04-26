package ir.amir.isnta.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.amir.isnta.data.dataSore.DataStore
import ir.amir.isnta.data.service.InitializeRetrofit
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) = DataStore(context)

    @Provides
    @Singleton
    fun provideRetrofit() = InitializeRetrofit().retrofit()

}