package com.project.animeinsights.presentation

import com.project.animeinsights.data.models.AnimeData
import com.project.animeinsights.data.models.Data
import com.project.animeinsights.data.models.anime_details.AnimeDetails

data class AnimeDetailsState(
    val isLoading: Boolean = false,
    val animeDetails: AnimeDetails? = null,
    val isFailure: String = ""
)
