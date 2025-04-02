package com.project.animeinsights.data.remote

import com.project.animeinsights.data.models.AnimeData
import com.project.animeinsights.data.models.Data
import com.project.animeinsights.data.models.anime_details.AnimeDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeIApiService {

    @GET("/v4/top/anime")
    suspend fun getAnimeList(): AnimeData

    @GET("/v4/anime/{anime_id}")
    suspend fun getAnimeDetail(@Path("anime_id") animeId: Int): AnimeDetails
}