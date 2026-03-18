package com.example.swapi_starships_mod_b8_share_detail.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi_starships_mod_b8_share_detail.domain.model.StarshipDetail
import com.example.swapi_starships_mod_b8_share_detail.domain.repository.StarshipRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class StarshipDetailUiState {
    data object Loading : StarshipDetailUiState()
    data class Content(val detail: StarshipDetail) : StarshipDetailUiState()
    data class Error(val message: String) : StarshipDetailUiState()
}

@HiltViewModel
class StarshipDetailViewModel @Inject constructor(
    private val repository: StarshipRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val starshipId: String = checkNotNull(savedStateHandle["starshipId"])

    var uiState: StarshipDetailUiState by mutableStateOf(StarshipDetailUiState.Loading)
        private set

    init {
        loadDetail()
    }

    private fun loadDetail() {
        viewModelScope.launch {
            uiState = StarshipDetailUiState.Loading
            repository.getStarshipDetail(starshipId)
                .onSuccess { detail ->
                    uiState = StarshipDetailUiState.Content(detail)
                }
                .onFailure { e ->
                    uiState = StarshipDetailUiState.Error(
                        e.message ?: "Не удалось загрузить данные корабля"
                    )
                }
        }
    }

    fun retry() = loadDetail()
}
