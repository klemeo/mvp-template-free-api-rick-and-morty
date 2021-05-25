package ru.android.rickandmortymvp.ui.mappers

import org.koin.core.KoinComponent
import ru.android.rickandmortymvp.app.models.data.episode.Episode
import ru.android.rickandmortymvp.app.models.data.episode_pres_model.EpisodePresModel

class EpisodeToPresModelMapper : KoinComponent {

    fun map(from: Episode) = EpisodePresModel(
        airDate = from.airDate,
        characters = from.characters,
        created = from.created,
        episode = from.episode,
        id = from.id,
        name = from.name,
        url = from.url
    )

}