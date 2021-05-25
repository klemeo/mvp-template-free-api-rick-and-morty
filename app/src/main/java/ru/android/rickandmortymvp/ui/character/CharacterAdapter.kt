package ru.android.rickandmortymvp.ui.character

import kotlinx.android.synthetic.main.item_number.view.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.base.RecyclerViewAdapter

class CharacterAdapter : RecyclerViewAdapter<String>() {

    var onClick: (Int) -> Unit = {}

    override val viewHolderLayoutId: Int = R.layout.item_number

    override fun bindModel(holder: ViewHolder, model: String) {
        with(holder.itemView) {

            numberView.text = model.replace("https://rickandmortyapi.com/api/episode/", "")

            setOnClickListener { onClick(model.replace("https://rickandmortyapi.com/api/episode/", "").toInt()) }

        }
    }

}