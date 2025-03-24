package ru.android.rickandmortymvp.ui.episode

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.episode_pres_model.EpisodePresModel
import ru.android.rickandmortymvp.base.MvpFragment
import ru.android.rickandmortymvp.base.args
import ru.android.rickandmortymvp.ui.utils.*

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

    private var buttonBack: Button? = null
    private var linearLayout: LinearLayout? = null
    private var idTextView: TextView? = null
    private var nameTextView: TextView? = null
    private var urlTextView: TextView? = null
    private var episodeTextView: TextView? = null
    private var createdTextView: TextView? = null
    private var airDateTextView: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var pbPost: ProgressBar? = null

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(view) {
            buttonBack = findViewById(R.id.buttonBack)
            linearLayout = findViewById(R.id.linearLayout)
            idTextView = findViewById(R.id.idTextView)
            nameTextView = findViewById(R.id.nameTextView)
            urlTextView = findViewById(R.id.urlTextView)
            episodeTextView = findViewById(R.id.episodeTextView)
            createdTextView = findViewById(R.id.createdTextView)
            airDateTextView = findViewById(R.id.airDateTextView)
            recyclerView = findViewById(R.id.recyclerView)
            pbPost = findViewById(R.id.pbPost)
        }

        recyclerView?.apply {
            layoutManager = GridLayoutManager(context, 5)
            adapter = episodeAdapter
        }

        buttonBack?.setOnClickListener { presenter.closeScreen() }

    }

    override fun refreshEpisode(episode: EpisodePresModel) {
        idTextView?.text = episode.id.toString()
        nameTextView?.text = episode.name
        urlTextView?.text = episode.url
        episodeTextView?.text = episode.episode
        createdTextView?.text = episode.created
        airDateTextView?.text = episode.airDate

        episode.characters?.let { episodeAdapter.setData(it) }
    }

    override fun showEpisode(animated: Boolean) {
        when (animated) {
            true -> {
                linearLayout?.visible()
                pbPost?.gone()
            }

            else -> {
                linearLayout?.gone()
                pbPost?.visible()
            }
        }
    }
}