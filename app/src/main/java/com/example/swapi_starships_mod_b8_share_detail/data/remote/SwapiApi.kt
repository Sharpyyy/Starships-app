package com.example.swapi_starships_mod_b8_share_detail.data.remote

import com.example.swapi_starships_mod_b8_share_detail.data.dto.StarshipDto
import com.example.swapi_starships_mod_b8_share_detail.data.dto.StarshipsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SwapiApi {

    @GET("starships/")
    suspend fun getStarships(@Query("page") page: Int = 1): StarshipsResponseDto

    @GET("starships/{id}/")
    suspend fun getStarshipById(@Path("id") id: String): StarshipDto
}
