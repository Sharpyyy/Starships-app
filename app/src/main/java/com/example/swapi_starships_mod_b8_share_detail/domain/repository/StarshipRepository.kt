package com.example.swapi_starships_mod_b8_share_detail.domain.repository

import com.example.swapi_starships_mod_b8_share_detail.domain.model.Starship
import com.example.swapi_starships_mod_b8_share_detail.domain.model.StarshipDetail

interface StarshipRepository {
    suspend fun getStarshipsList(page: Int = 1): Result<List<Starship>>
    suspend fun getStarshipDetail(id: String): Result<StarshipDetail>
}
