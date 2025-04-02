package com.project.animeinsights.di

import com.project.animeinsights.common.Constants
import com.project.animeinsights.data.remote.AnimeIApiService
import com.project.animeinsights.data.repo.AnimeRepositoryImpl
import com.project.animeinsights.domain.repo.AnimeRepository
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
    fun provideAnimeApiService(): AnimeIApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeIApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesCryptoRepository(apiService: AnimeIApiService): AnimeRepository {
        return AnimeRepositoryImpl(apiService)
    }
}