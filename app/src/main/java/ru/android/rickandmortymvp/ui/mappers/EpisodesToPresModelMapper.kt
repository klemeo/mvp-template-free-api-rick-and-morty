package ru.android.rickandmortymvp.ui.mappers

import ru.android.rickandmortymvp.app.models.data.episode.Episodes
import ru.android.rickandmortymvp.app.models.data.episode_pres_model.*

fun Episodes.toMap() = EpisodesPresModel(
        info = info.let { infoData ->
            InfoPresModel(
                count = infoData?.count,
                next = infoData?.next,
                pages = infoData?.pages,
                prev = infoData?.prev,
            )
        },
        results = results?.map { resultData ->
            EpisodePresModel(
                airDate = resultData.airDate,
                characters = resultData.characters,
                created = resultData.created,
                episode = resultData.episode,
                id = resultData.id,
                name = resultData.name,
                url = resultData.url
            )
        }
    )