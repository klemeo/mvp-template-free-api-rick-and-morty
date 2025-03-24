package ru.android.rickandmortymvp.ui.character

import android.widget.TextView
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.base.RecyclerViewAdapter
import ru.android.rickandmortymvp.ui.utils.pageEpisode

class CharacterAdapter : RecyclerViewAdapter<String>() {

    var onClick: (Int) -> Unit = {}

    override val viewHolderLayoutId: Int = R.layout.item_number

    override fun bindModel(holder: ViewHolder, model: String) {
        with(holder.itemView) {
            val numberView = findViewById<TextView>(R.id.numberView)

            numberView.text = model.pageEpisode().toString()

            setOnClickListener {
                onClick(
                    model.pageEpisode()
                )
            }

        }
    }

}