package ru.android.rickandmortymvp.ui.character

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

    private var buttonBack: Button? = null
    private var imagePreview: ImageView? = null
    private var textName: TextView? = null
    private var textStatus: TextView? = null
    private var textLocation: TextView? = null
    private var textFirstSeen: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var pbPost: FrameLayout? = null
    private var vgContent: LinearLayout? = null

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(view) {
            buttonBack = findViewById(R.id.buttonBack)
            imagePreview = findViewById(R.id.imagePreview)
            textName = findViewById(R.id.textName)
            textStatus = findViewById(R.id.textStatus)
            textLocation = findViewById(R.id.textLocation)
            textFirstSeen = findViewById(R.id.textFirstSeen)
            recyclerView = findViewById(R.id.recyclerView)
            pbPost = findViewById(R.id.pbPost)
            vgContent = findViewById(R.id.vgContent)
        }

        recyclerView?.apply {
            layoutManager = GridLayoutManager(context, 5)
            adapter = characterAdapter
        }

        buttonBack?.setOnClickListener { presenter.closeScreen() }

    }

    override fun refreshCharacter(character: CharacterPresModel) {
        imagePreview?.apply {
            Glide.with(context)
                .load(character.image)
                .into(this)
        }

        textName?.text = character.name
        textStatus?.text = character.status
        textLocation?.text = character.origin?.name

        when (character.status) {
            "Alive" -> textStatus?.getColorGreen()
            "Dead" -> textStatus?.getColorRed()
            else -> textStatus?.getColorGrey()
        }


        character.episode?.let { characterAdapter.setData(it) }
    }

    override fun showCharacter(animated: Boolean) {
        when (animated) {
            true -> {
                vgContent?.visible()
                pbPost?.gone()
            }

            else -> {
                vgContent?.gone()
                pbPost?.visible()
            }
        }
    }
}