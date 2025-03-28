package ru.android.rickandmortymvp.ui.episodes

import android.widget.TextView
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.episode_pres_model.EpisodePresModel
import ru.android.rickandmortymvp.base.RecyclerViewAdapter

class EpisodesAdapter : RecyclerViewAdapter<EpisodePresModel>() {

    var onClick: (EpisodePresModel) -> Unit = {}

    override val viewHolderLayoutId: Int = R.layout.item_episode

    override fun bindModel(holder: ViewHolder, model: EpisodePresModel) {
        with(holder.itemView) {

            val textName = findViewById<TextView>(R.id.textName)

            textName.text = model.name

            setOnClickListener { onClick(model) }

        }
    }
}