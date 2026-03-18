package com.example.swapi_starships_mod_b8_share_detail.data.repository

import com.example.swapi_starships_mod_b8_share_detail.data.mapper.toStarship
import com.example.swapi_starships_mod_b8_share_detail.data.mapper.toStarshipDetail
import com.example.swapi_starships_mod_b8_share_detail.data.remote.SwapiApi
import com.example.swapi_starships_mod_b8_share_detail.domain.model.Starship
import com.example.swapi_starships_mod_b8_share_detail.domain.model.StarshipDetail
import com.example.swapi_starships_mod_b8_share_detail.domain.repository.StarshipRepository
import javax.inject.Inject

class StarshipRepositoryImpl @Inject constructor(
    private val api: SwapiApi
) : StarshipRepository {

    override suspend fun getStarshipsList(page: Int): Result<List<Starship>> {
        return runCatching {
            val response = api.getStarships(page)
            response.results.map { it.toStarship() }
        }
    }

    override suspend fun getStarshipDetail(id: String): Result<StarshipDetail> {
        return runCatching {
            api.getStarshipById(id).toStarshipDetail()
        }
    }
}
