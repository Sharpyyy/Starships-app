package com.example.swapi_starships_mod_b8_share_detail.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.swapi_starships_mod_b8_share_detail.domain.model.Starship

private const val VARIANT_CODE = "SWAPI-STARSHIPS-MOD_B8_SHARE_DETAIL"

@Composable
fun StarshipsListScreen(
    onStarshipClick: (String) -> Unit,
    viewModel: StarshipsListViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
            tonalElevation = 0.dp
        ) {
            Text(
                text = "VariantCode: $VARIANT_CODE",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
        when (val state = uiState) {
            is StarshipsListUiState.Loading -> LoadingContent()
            is StarshipsListUiState.Content -> StarshipsContent(
                starships = state.starships,
                onStarshipClick = onStarshipClick
            )
            is StarshipsListUiState.Error -> ErrorContent(
                message = state.message,
                onRetry = viewModel::retry
            )
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun StarshipsContent(
    starships: List<Starship>,
    onStarshipClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(starships, key = { it.id }) { starship ->
                StarshipItem(
                    starship = starship,
                    onClick = { onStarshipClick(starship.id) }
                )
            }
        }
    )
}

@Composable
private fun StarshipItem(
    starship: Starship,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = starship.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = starship.model,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = starship.starshipClass,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Button(onClick = onRetry) {
                Text("Повторить")
            }
        }
    }
}
