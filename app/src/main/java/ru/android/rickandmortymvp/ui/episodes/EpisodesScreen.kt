package ru.android.rickandmortymvp.ui.episodes

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.fragment_episodes.*
import kotlinx.android.synthetic.main.fragment_episodes.backButton
import kotlinx.android.synthetic.main.fragment_episodes.buttonBack
import kotlinx.android.synthetic.main.fragment_episodes.nextButton
import kotlinx.android.synthetic.main.fragment_episodes.pbPost
import kotlinx.android.synthetic.main.fragment_episodes.recyclerView
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.episode_pres_model.EpisodesPresModel
import ru.android.rickandmortymvp.base.MvpFragment

class EpisodesScreen : MvpFragment<Presenter>(), View {

    override val presenter by lazy {
        Presenter(
            view = this
        )
    }

    private var prevPage: Int? = null

    private var nextPage: Int? = null

    override val layout: Int = R.layout.fragment_episodes

    private val episodesAdapter by lazy {
        EpisodesAdapter().apply {
            onClick = { it.id?.let { episodeId -> presenter.showEpisode(episodeId) } }
        }
    }

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, 1)
            adapter = episodesAdapter
        }

        buttonBack.setOnClickListener { presenter.closeScreen() }

        nextButton.setOnClickListener {
            presenter.loadEpisodes(nextPage)
        }

        backButton.setOnClickListener {
            presenter.loadEpisodes(prevPage)
        }


    }

    override fun refreshEpisodes(episodes: EpisodesPresModel) {
        episodes.results?.let { episodesAdapter.setData(it) }
        nextPage = episodes.info?.next?.replace(
            "https://rickandmortyapi.com/api/episode?page=",
            ""
        )?.toInt()
        prevPage =
            if (episodes.info?.prev != null) episodes.info.prev.toString()
                .replace("https://rickandmortyapi.com/api/episode?page=", "")
                .toInt() else null
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