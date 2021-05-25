package ru.android.rickandmortymvp.ui.mappers

import org.koin.core.KoinComponent
import ru.android.rickandmortymvp.app.models.data.character.Characters
import ru.android.rickandmortymvp.app.models.data.character_pres_model.*

class CharactersToPresModelMapper : KoinComponent {

    fun map(from: Characters) = CharactersPresModel(
        info = from.info.let { infoData ->
            InfoPresModel(
                count = infoData?.count,
                next = infoData?.next,
                pages = infoData?.pages,
                prev = infoData?.prev,
            )
        },
        results = from.results?.map { resultData ->
            CharacterPresModel(
                created = resultData.created,
                episode = resultData.episode,
                gender = resultData.gender,
                id = resultData.id,
                image = resultData.image,
                location = resultData.location.let { locationData ->
                    LocationPresModel(
                        name = locationData?.name,
                        url = locationData?.url
                    )
                },
                name = resultData.name,
                origin = resultData.origin.let { originData ->
                    OriginPresModel(
                        name = originData?.name,
                        url = originData?.url
                    )
                },
                species = resultData.species,
                status = resultData.status,
                type = resultData.type,
                url = resultData.url,
            )
        }
    )
}