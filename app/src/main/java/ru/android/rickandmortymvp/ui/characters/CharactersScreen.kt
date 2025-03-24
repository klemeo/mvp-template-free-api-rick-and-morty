package ru.android.rickandmortymvp.ui.characters

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.character_pres_model.CharactersPresModel
import ru.android.rickandmortymvp.base.MvpFragment
import ru.android.rickandmortymvp.ui.utils.gone
import ru.android.rickandmortymvp.ui.utils.pageCharacters
import ru.android.rickandmortymvp.ui.utils.visible

class CharactersScreen : MvpFragment<Presenter>(), View {

    override val presenter by lazy {
        Presenter(
            view = this
        )
    }

    private var prevPage: Int? = null

    private var nextPage: Int? = null

    override val layout: Int = R.layout.fragment_characters

    private val charactersAdapter by lazy {
        CharactersAdapter().apply {
            onClick = { it.id?.let { characterId -> presenter.showCharacter(characterId) } }
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
            adapter = charactersAdapter
        }

        buttonBack?.setOnClickListener { presenter.closeScreen() }

        nextButton?.setOnClickListener {
            presenter.loadCharacters(nextPage)
        }

        backButton?.setOnClickListener {
            presenter.loadCharacters(prevPage)
        }

    }

    override fun refreshCharacters(characters: CharactersPresModel) {
        characters.results?.let { charactersAdapter.setData(it) }
        nextPage = if (characters.info?.next != null) characters.info.next
            .pageCharacters() else null
        when {
            nextPage != null -> nextButton?.visible()
            nextPage == null -> nextButton?.gone()
        }
        prevPage = if (characters.info?.prev != null) characters.info.prev.toString()
            .pageCharacters() else null
        when {
            prevPage != null -> backButton?.visible()
            prevPage == null -> backButton?.gone()
        }
    }

    override fun showCharacters(animated: Boolean) {
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