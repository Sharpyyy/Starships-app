package com.example.swapi_starships_mod_b8_share_detail.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi_starships_mod_b8_share_detail.domain.model.StarshipDetail
import com.example.swapi_starships_mod_b8_share_detail.domain.repository.StarshipRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class StarshipDetailUiState {
    data object Loading : StarshipDetailUiState()
    data class Content(val detail: StarshipDetail) : StarshipDetailUiState()
    data class Error(val message: String) : StarshipDetailUiState()
}

/**
 * ViewModel экрана детали корабля.
 *
 * Почему StateFlow, а не mutableStateOf?
 * - mutableStateOf живёт в Compose runtime — для использования из ViewModel пришлось бы
 *   передавать в ViewModel scope/CompositionLocal или хранить state в Activity, что ломает
 *   разделение слоёв и усложняет тесты.
 * - StateFlow — рекомендуемый способ экспозиции UI state из ViewModel: не зависит от Compose,
 *   переживает смену конфигурации, удобен для асинхронных обновлений в coroutines и даёт
 *   один источник правды для нескольких подписчиков (collectAsState в Compose подписывается на Flow).
 */
@HiltViewModel
class StarshipDetailViewModel @Inject constructor(
    private val repository: StarshipRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val starshipId: String = checkNotNull(savedStateHandle["starshipId"])

    private val _uiState = MutableStateFlow<StarshipDetailUiState>(StarshipDetailUiState.Loading)
    val uiState: StateFlow<StarshipDetailUiState> = _uiState.asStateFlow()

    init {
        loadDetail()
    }

    private fun loadDetail() {
        viewModelScope.launch {
            _uiState.value = StarshipDetailUiState.Loading
            repository.getStarshipDetail(starshipId)
                .onSuccess { detail ->
                    _uiState.value = StarshipDetailUiState.Content(detail)
                }
                .onFailure { e ->
                    _uiState.value = StarshipDetailUiState.Error(
                        e.message ?: "Не удалось загрузить данные корабля"
                    )
                }
        }
    }

    fun retry() = loadDetail()
}
