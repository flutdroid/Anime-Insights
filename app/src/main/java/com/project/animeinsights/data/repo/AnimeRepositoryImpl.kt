package com.project.animeinsights.data.repo

import android.util.Log
import com.project.animeinsights.data.models.AnimeData
import com.project.animeinsights.data.models.Data
import com.project.animeinsights.data.models.anime_details.AnimeDetails
import com.project.animeinsights.data.remote.AnimeIApiService
import com.project.animeinsights.domain.repo.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeIApiService: AnimeIApiService
) : AnimeRepository {
    override suspend fun getAnimeList(): AnimeData {
        return animeIApiService.getAnimeList()
    }

    override suspend fun getAnimeDetails(animeId: Int): AnimeDetails {
        return animeIApiService.getAnimeDetail(animeId = animeId)
    }
}