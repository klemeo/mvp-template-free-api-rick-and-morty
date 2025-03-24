package ru.android.rickandmortymvp.ui.characters

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.character_pres_model.CharacterPresModel
import ru.android.rickandmortymvp.base.RecyclerViewAdapter
import ru.android.rickandmortymvp.ui.utils.*

class CharactersAdapter : RecyclerViewAdapter<CharacterPresModel>() {

    var onClick: (CharacterPresModel) -> Unit = {}

    override val viewHolderLayoutId: Int = R.layout.item_character

    override fun bindModel(holder: ViewHolder, model: CharacterPresModel) {
        with(holder.itemView) {

            val imagePreview: ImageView = findViewById(R.id.imagePreview)
            val textName: TextView = findViewById(R.id.textName)
            val textStatus: TextView = findViewById(R.id.textStatus)
            val textLocation: TextView = findViewById(R.id.textLocation)
            val textFirstSeen: TextView = findViewById(R.id.textFirstSeen)

            Glide.with(context)
                .load(model.image)
                .into(imagePreview)

            textName.text = model.name
            textStatus.text = model.status
            textLocation.text = model.origin?.name
            textFirstSeen.text = model.episode?.first()

            when (model.status) {
                "Alive" -> textStatus.getColorGreen()
                "Dead" -> textStatus.getColorRed()
                else -> textStatus.getColorGrey()
            }

            setOnClickListener { onClick(model) }

        }
    }
}