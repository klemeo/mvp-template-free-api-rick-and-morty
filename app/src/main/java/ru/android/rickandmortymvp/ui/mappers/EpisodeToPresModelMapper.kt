package ru.android.rickandmortymvp.ui.mappers

import ru.android.rickandmortymvp.app.models.data.episode.Episode
import ru.android.rickandmortymvp.app.models.data.episode_pres_model.EpisodePresModel

fun Episode.toMap() = EpisodePresModel(
        airDate = airDate,
        characters = characters,
        created = created,
        episode = episode,
        id = id,
        name = name,
        url = url
    )