package ru.android.rickandmortymvp.ui.mappers

import ru.android.rickandmortymvp.app.models.data.character.Character
import ru.android.rickandmortymvp.app.models.data.character_pres_model.*

fun Character.toMap() = CharacterPresModel(
    created = created,
    episode = episode,
    gender = gender,
    id = id,
    image = image,
    location = location.let { locationData ->
        LocationPresModel(
            name = locationData?.name,
            url = locationData?.url
        )
    },
    name = name,
    origin = origin.let { originData ->
        OriginPresModel(
            name = originData?.name,
            url = originData?.url
        )
    },
    species = species,
    status = status,
    type = type,
    url = url,
)