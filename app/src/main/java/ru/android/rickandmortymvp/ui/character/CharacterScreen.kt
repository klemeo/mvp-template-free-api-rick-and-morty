package ru.android.rickandmortymvp.ui.character

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_character.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.character_pres_model.CharacterPresModel
import ru.android.rickandmortymvp.base.MvpFragment
import ru.android.rickandmortymvp.base.args
import ru.android.rickandmortymvp.ui.utils.*

class CharacterScreen : MvpFragment<Presenter>(), View {

    companion object {

        fun newInstance(
            characterId: Int,
        ) = CharacterScreen().args {
            putInt(ARG_CHARACTER_ID, characterId)
        }

        private const val ARG_CHARACTER_ID = "ARG_CHARACTER_ID"
    }

    override val presenter by lazy {
        Presenter(
            view = this,
            characterId = requireArguments().getInt(ARG_CHARACTER_ID)
        )
    }

    override val layout: Int = R.layout.fragment_character

    private val characterAdapter by lazy {
        CharacterAdapter().apply {
            onClick = { presenter.showEpisode(it) }
        }
    }

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, 5)
            adapter = characterAdapter
        }

        buttonBack.setOnClickListener { presenter.closeScreen() }

    }

    override fun refreshCharacter(character: CharacterPresModel) {
        context?.let { context ->
            Glide.with(context)
                .load(character.image)
                .into(imagePreview)
        }

        textName.text = character.name
        textStatus.text = character.status
        textLocation.text = character.origin?.name

        when (character.status) {
            "Alive" -> textStatus.getColorGreen()
            "Dead" -> textStatus.getColorRed()
            else -> textStatus.getColorGrey()
        }


        character.episode?.let { characterAdapter.setData(it) }
    }

    override fun showCharacter(animated: Boolean) {
        when (animated) {
            true -> {
                linearLayout.visible()
                pbPost.gone()
            }
            else -> {
                linearLayout.gone()
                pbPost.visible()
            }
        }
    }
}