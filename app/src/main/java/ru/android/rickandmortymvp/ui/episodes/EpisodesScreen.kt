package ru.android.rickandmortymvp.ui.episodes

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_episodes.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.episode_pres_model.EpisodesPresModel
import ru.android.rickandmortymvp.base.MvpFragment

class EpisodesScreen: MvpFragment<Presenter>(), View {

    override val presenter by lazy {
        Presenter(
            view = this
        )
    }

    override val layout: Int = R.layout.fragment_episodes

    private val episodesAdapter by lazy {
        EpisodesAdapter().apply {
            onClick = { }
        }
    }

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, 1)
            adapter = episodesAdapter
        }

        buttonBack.setOnClickListener { presenter.closeScreen() }

    }

    override fun refreshEpisodes(characters: EpisodesPresModel) {
        characters.results?.let { episodesAdapter.setData(it) }
    }

    override fun showEpisodes(animated: Boolean) {
        when (animated) {
            true -> {
                recyclerView.isVisible = true
                pbPost.isGone = true
            }
            else -> {
                recyclerView.isGone = true
                pbPost.isVisible = true
            }
        }
    }
}