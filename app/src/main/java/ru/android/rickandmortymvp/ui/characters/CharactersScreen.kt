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

    override val layout: Int = R.layout.fragment_characters

    private val charactersAdapter by lazy {
        CharactersAdapter().apply {
            onClick = { }
        }
    }

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, 1)
            adapter = charactersAdapter
        }

        buttonBack.setOnClickListener { presenter.closeScreen() }

    }

    override fun refreshCharacters(characters: CharactersPresModel) {
        characters.results?.let { charactersAdapter.setData(it) }
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