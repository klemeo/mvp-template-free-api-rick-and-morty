package ru.android.rickandmortymvp.ui.episodes

import ru.android.rickandmortymvp.app.models.data.episode_pres_model.EpisodesPresModel

interface View {

    fun refreshEpisodes(episodes: EpisodesPresModel)

    fun showEpisodes(animated: Boolean)

}