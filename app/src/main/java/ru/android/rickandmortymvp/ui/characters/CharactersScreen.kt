package ru.android.rickandmortymvp.ui.characters

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_characters.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.character_pres_model.CharactersPresModel
import ru.android.rickandmortymvp.base.MvpFragment

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

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, 1)
            adapter = charactersAdapter
        }

        backButton.isGone = true
        nextButton.isGone = true

        buttonBack.setOnClickListener { presenter.closeScreen() }

        nextButton.setOnClickListener {
            presenter.loadCharacters(nextPage)
        }

        backButton.setOnClickListener {
            presenter.loadCharacters(prevPage)
        }

    }

    override fun refreshCharacters(characters: CharactersPresModel) {
        characters.results?.let { charactersAdapter.setData(it) }
        nextPage = if (characters.info?.next != null) characters.info.next.replace(
            "https://rickandmortyapi.com/api/character?page=",
            ""
        ).toInt() else null
        when {
            nextPage != null -> nextButton.isGone = false
            nextPage == null -> nextButton.isGone = true
        }
        prevPage = if (characters.info?.prev != null) characters.info.prev.toString()
            .replace("https://rickandmortyapi.com/api/character?page=", "")
            .toInt() else null
        when {
            prevPage != null -> backButton.isGone = false
            prevPage == null -> backButton.isGone = true
        }
    }

    override fun showCharacters(animated: Boolean) {
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