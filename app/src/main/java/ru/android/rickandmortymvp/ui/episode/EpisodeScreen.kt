package ru.android.rickandmortymvp.ui.episode

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_episode.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.episode_pres_model.EpisodePresModel
import ru.android.rickandmortymvp.base.MvpFragment
import ru.android.rickandmortymvp.base.args

class EpisodeScreen : MvpFragment<Presenter>(), View {

    companion object {

        fun newInstance(
            episodeId: Int,
        ) = EpisodeScreen().args {
            putInt(ARG_CHARACTER_ID, episodeId)
        }

        private const val ARG_CHARACTER_ID = "ARG_CHARACTER_ID"
    }

    override val presenter by lazy {
        Presenter(
            view = this,
            episodeId = requireArguments().getInt(ARG_CHARACTER_ID)
        )
    }

    override val layout: Int = R.layout.fragment_episode

    private val episodeAdapter by lazy {
        EpisodeAdapter().apply {
            onClick = { presenter.showCharacter(it) }
        }
    }

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, 5)
            adapter = episodeAdapter
        }

        buttonBack.setOnClickListener { presenter.closeScreen() }

    }

    override fun refreshEpisode(episode: EpisodePresModel) {
        idTextView.text = episode.id.toString()
        nameTextView.text = episode.name
        urlTextView.text = episode.url
        episodeTextView.text = episode.episode
        createdTextView.text = episode.created
        airDateTextView.text = episode.airDate


        episode.characters?.let { episodeAdapter.setData(it) }
    }

    override fun showEpisode(animated: Boolean) {
        when (animated) {
            true -> {
                linearLayout.isVisible = true
                pbPost.isGone = true
            }
            else -> {
                linearLayout.isGone = true
                pbPost.isVisible = true
            }
        }
    }
}