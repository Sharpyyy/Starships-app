package com.example.swapi_starships_mod_b8_share_detail.domain.model

data class Starship(
    val id: String,
    val name: String,
    val model: String,
    val manufacturer: String,
    val starshipClass: String
)

data class StarshipDetail(
    val id: String,
    val name: String,
    val model: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val maxAtmospheringSpeed: String,
    val crew: String,
    val passengers: String,
    val cargoCapacity: String,
    val consumables: String,
    val hyperdriveRating: String,
    val mglt: String,
    val starshipClass: String
)
