package ru.android.rickandmortymvp.ui.episode

import kotlinx.android.synthetic.main.item_episode.view.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.base.RecyclerViewAdapter

class EpisodeAdapter : RecyclerViewAdapter<String>() {

    var onClick: (Int) -> Unit = {}

    override val viewHolderLayoutId: Int = R.layout.item_episode

    override fun bindModel(holder: ViewHolder, model: String) {
        with(holder.itemView) {

            textName.text = model.replace("https://rickandmortyapi.com/api/character/", "")

            setOnClickListener { onClick(model.replace("https://rickandmortyapi.com/api/character/", "").toInt()) }

        }
    }

}