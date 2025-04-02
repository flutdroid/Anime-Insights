package com.project.animeinsights.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.animeinsights.common.Constants
import com.project.animeinsights.common.Resource
import com.project.animeinsights.data.models.AnimeData
import com.project.animeinsights.domain.use_case.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeUseCase: AnimeUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AnimeListState())
    val state: State<AnimeListState> = _state

    private val _animeDetailsState = mutableStateOf(AnimeDetailsState())
    val animeDetailsState: State<AnimeDetailsState> = _animeDetailsState

    private val _animeTextFieldValue = mutableStateOf("")
    val animeTextFieldValue: State<String> = _animeTextFieldValue
    private val malId = savedStateHandle.get<String>(Constants.ANIME_ID)?.toIntOrNull()


    init {
        getAnimeList()

    }
    init {
        malId?.let { id ->
            getAnimeDetails(id)
        }
    }

    private fun getAnimeList() {
        animeUseCase.getAnimeList().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = AnimeListState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value =
                        AnimeListState(isFailure = result.message ?: "No internet connection")
                }

                is Resource.Success -> {
                    _state.value = AnimeListState(animeList = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAnimeDetails(animeId: Int) {
        Log.d("animeIdViewModel",animeId.toString())
        animeUseCase.getAnimeDetails(animeId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _animeDetailsState.value =
                        AnimeDetailsState(isFailure = result.message ?: "No internet connection")
                }

                is Resource.Success -> {
                    _animeDetailsState.value = AnimeDetailsState(animeDetails = result.data)
                }

                is Resource.Loading -> {
                    _animeDetailsState.value = AnimeDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}