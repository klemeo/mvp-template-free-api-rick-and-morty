package ru.android.rickandmortymvp.ui.mappers

import org.koin.core.KoinComponent
import ru.android.rickandmortymvp.app.models.data.episode.Episodes
import ru.android.rickandmortymvp.app.models.data.episode_pres_model.*

class EpisodesToPresModelMapper : KoinComponent {

    fun map(from: Episodes) = EpisodesPresModel(
        info = from.info.let { infoData ->
            InfoPresModel(
                count = infoData?.count,
                next = infoData?.next,
                pages = infoData?.pages,
                prev = infoData?.prev,
            )
        },
        results = from.results?.map { resultData ->
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

}