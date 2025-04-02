package com.project.animeinsights.presentation

import com.project.animeinsights.data.models.AnimeData

data class AnimeListState(
    val isLoading: Boolean = false,
    val animeList: AnimeData? = null,
    val isFailure: String = ""
)
