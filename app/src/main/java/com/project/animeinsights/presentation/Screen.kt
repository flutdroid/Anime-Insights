package com.project.animeinsights.presentation

sealed class Screen(val route: String) {
    data object AnimeSplashScreen: Screen("anime_splash_screen")
    data object AnimeListScreen: Screen("anime_list_screen")
    data object AnimeDetailScreen: Screen("anime_detail_screen")
}