package com.project.animeinsights.domain.repo

import com.project.animeinsights.data.models.AnimeData
import com.project.animeinsights.data.models.Data
import com.project.animeinsights.data.models.anime_details.AnimeDetails

interface AnimeRepository {

    suspend fun getAnimeList(): AnimeData

    suspend fun getAnimeDetails(animeId: Int): AnimeDetails
}