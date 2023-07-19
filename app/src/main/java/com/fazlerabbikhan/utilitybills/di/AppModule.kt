package com.fazlerabbikhan.utilitybills.di

import com.fazlerabbikhan.utilitybills.common.Constants
import com.fazlerabbikhan.utilitybills.data.remote.ApiService
import com.fazlerabbikhan.utilitybills.data.repository.UtilityRepositoryImpl
import com.fazlerabbikhan.utilitybills.domain.repository.UtilityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: ApiService): UtilityRepository {
        return UtilityRepositoryImpl(api)
    }
}