package com.example.swapi_starships_mod_b8_share_detail.data.mapper

import com.example.swapi_starships_mod_b8_share_detail.data.dto.StarshipDto
import com.example.swapi_starships_mod_b8_share_detail.domain.model.Starship
import com.example.swapi_starships_mod_b8_share_detail.domain.model.StarshipDetail

fun StarshipDto.extractId(): String {
    return url.trimEnd('/').substringAfterLast('/')
}

fun StarshipDto.toStarship(): Starship {
    return Starship(
        id = extractId(),
        name = name,
        model = model,
        manufacturer = manufacturer,
        starshipClass = starshipClass
    )
}

fun StarshipDto.toStarshipDetail(): StarshipDetail {
    return StarshipDetail(
        id = extractId(),
        name = name,
        model = model,
        manufacturer = manufacturer,
        costInCredits = costInCredits,
        length = length,
        maxAtmospheringSpeed = maxAtmospheringSpeed,
        crew = crew,
        passengers = passengers,
        cargoCapacity = cargoCapacity,
        consumables = consumables,
        hyperdriveRating = hyperdriveRating,
        mglt = mglt,
        starshipClass = starshipClass
    )
}
