package ru.android.rickandmortymvp.ui.episode

import ru.android.rickandmortymvp.app.models.data.episode_pres_model.EpisodePresModel

interface View {

    fun refreshEpisode(episode: EpisodePresModel)

    fun showEpisode(animated: Boolean)

}