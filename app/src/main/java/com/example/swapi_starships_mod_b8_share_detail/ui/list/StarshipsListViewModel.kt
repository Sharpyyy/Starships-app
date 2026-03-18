package com.example.swapi_starships_mod_b8_share_detail.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi_starships_mod_b8_share_detail.domain.model.Starship
import com.example.swapi_starships_mod_b8_share_detail.domain.repository.StarshipRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class StarshipsListUiState {
    data object Loading : StarshipsListUiState()
    data class Content(val starships: List<Starship>) : StarshipsListUiState()
    data class Error(val message: String) : StarshipsListUiState()
}

@HiltViewModel
class StarshipsListViewModel @Inject constructor(
    private val repository: StarshipRepository
) : ViewModel() {

    var uiState: StarshipsListUiState by mutableStateOf(StarshipsListUiState.Loading)
        private set

    init {
        loadStarships()
    }

    fun loadStarships() {
        viewModelScope.launch {
            uiState = StarshipsListUiState.Loading
            repository.getStarshipsList(page = 1)
                .onSuccess { list ->
                    uiState = StarshipsListUiState.Content(list)
                }
                .onFailure { e ->
                    uiState = StarshipsListUiState.Error(
                        e.message ?: "Не удалось загрузить список кораблей"
                    )
                }
        }
    }

    fun retry() = loadStarships()
}
