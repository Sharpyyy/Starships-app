package com.example.swapi_starships_mod_b8_share_detail.data.dto

import com.google.gson.annotations.SerializedName

data class StarshipDto(
    @SerializedName("name") val name: String,
    @SerializedName("model") val model: String,
    @SerializedName("manufacturer") val manufacturer: String,
    @SerializedName("cost_in_credits") val costInCredits: String,
    @SerializedName("length") val length: String,
    @SerializedName("max_atmosphering_speed") val maxAtmospheringSpeed: String,
    @SerializedName("crew") val crew: String,
    @SerializedName("passengers") val passengers: String,
    @SerializedName("cargo_capacity") val cargoCapacity: String,
    @SerializedName("consumables") val consumables: String,
    @SerializedName("hyperdrive_rating") val hyperdriveRating: String,
    @SerializedName("MGLT") val mglt: String,
    @SerializedName("starship_class") val starshipClass: String,
    @SerializedName("url") val url: String
)

data class StarshipsResponseDto(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<StarshipDto>
)
