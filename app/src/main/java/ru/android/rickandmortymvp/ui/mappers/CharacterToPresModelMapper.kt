package ru.android.rickandmortymvp.ui.mappers

import org.koin.core.KoinComponent
import ru.android.rickandmortymvp.app.models.data.character.Character
import ru.android.rickandmortymvp.app.models.data.character_pres_model.*

class CharacterToPresModelMapper : KoinComponent {

    fun map(from: Character) = CharacterPresModel(
        created = from.created,
        episode = from.episode,
        gender = from.gender,
        id = from.id,
        image = from.image,
        location = from.location.let { locationData ->
            LocationPresModel(
                name = locationData?.name,
                url = locationData?.url
            )
        },
        name = from.name,
        origin = from.origin.let { originData ->
            OriginPresModel(
                name = originData?.name,
                url = originData?.url
            )
        },
        species = from.species,
        status = from.status,
        type = from.type,
        url = from.url,
    )

}