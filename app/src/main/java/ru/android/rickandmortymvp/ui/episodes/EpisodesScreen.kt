package ru.android.rickandmortymvp.ui.episodes

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.episode_pres_model.EpisodesPresModel
import ru.android.rickandmortymvp.base.MvpFragment
import ru.android.rickandmortymvp.ui.utils.gone
import ru.android.rickandmortymvp.ui.utils.pageEpisodes
import ru.android.rickandmortymvp.ui.utils.visible

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

    private var buttonBack: Button? = null
    private var recyclerView: RecyclerView? = null
    private var pbPost: ProgressBar? = null
    private var backButton: Button? = null
    private var nextButton: Button? = null

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(view) {
            buttonBack = findViewById(R.id.buttonBack)
            recyclerView = findViewById(R.id.recyclerView)
            pbPost = findViewById(R.id.pbPost)
            backButton = findViewById(R.id.backButton)
            nextButton = findViewById(R.id.nextButton)
        }

        recyclerView?.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = episodesAdapter
        }

        buttonBack?.setOnClickListener { presenter.closeScreen() }

        nextButton?.setOnClickListener {
            presenter.loadEpisodes(nextPage)
        }

        backButton?.setOnClickListener {
            presenter.loadEpisodes(prevPage)
        }


    }

    override fun refreshEpisodes(episodes: EpisodesPresModel) {
        episodes.results?.let { episodesAdapter.setData(it) }
        nextPage = if (episodes.info?.next != null) episodes.info.next
            .pageEpisodes() else null
        when {
            nextPage != null -> nextButton?.visible()
            nextPage == null -> nextButton?.gone()
        }
        prevPage = if (episodes.info?.prev != null) episodes.info.prev.toString()
            .pageEpisodes() else null
        when {
            prevPage != null -> backButton?.visible()
            prevPage == null -> backButton?.gone()
        }
    }

    override fun showEpisodes(animated: Boolean) {
        when (animated) {
            true -> {
                recyclerView?.visible()
                pbPost?.gone()
            }

            else -> {
                recyclerView?.gone()
                pbPost?.visible()
            }
        }
    }
}