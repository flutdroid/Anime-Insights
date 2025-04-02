package com.project.animeinsights.domain.use_case

import android.util.Log
import com.project.animeinsights.common.Resource
import com.project.animeinsights.data.models.AnimeData
import com.project.animeinsights.data.models.Data
import com.project.animeinsights.data.models.anime_details.AnimeDetails
import com.project.animeinsights.domain.repo.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AnimeUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    fun getAnimeList(): Flow<Resource<AnimeData>> = flow {
        try {
            emit(Resource.Loading())
            val animeData = animeRepository.getAnimeList()
            emit(Resource.Success(animeData))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "No internet connection"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "No internet connection"))
        }
    }

    fun getAnimeDetails(animeId: Int): Flow<Resource<AnimeDetails>> = flow {
        try {
            emit(Resource.Loading())
            val animeDetail = animeRepository.getAnimeDetails(animeId)
            emit(Resource.Success(animeDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "No internet connection"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "No internet connection"))
        }

    }
}