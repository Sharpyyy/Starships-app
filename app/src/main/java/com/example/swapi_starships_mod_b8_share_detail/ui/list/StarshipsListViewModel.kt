package com.example.swapi_starships_mod_b8_share_detail.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi_starships_mod_b8_share_detail.domain.model.Starship
import com.example.swapi_starships_mod_b8_share_detail.domain.repository.StarshipRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _uiState = MutableStateFlow<StarshipsListUiState>(StarshipsListUiState.Loading)
    val uiState: StateFlow<StarshipsListUiState> = _uiState.asStateFlow()

    init {
        loadStarships()
    }

    fun loadStarships() {
        viewModelScope.launch {
            _uiState.value = StarshipsListUiState.Loading
            repository.getStarshipsList(page = 1)
                .onSuccess { list ->
                    _uiState.value = StarshipsListUiState.Content(list)
                }
                .onFailure { e ->
                    _uiState.value = StarshipsListUiState.Error(
                        e.message ?: "Не удалось загрузить список кораблей"
                    )
                }
        }
    }

    fun retry() = loadStarships()
}
