package ru.android.rickandmortymvp.ui.episode

import kotlinx.android.synthetic.main.item_number.view.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.base.RecyclerViewAdapter
import ru.android.rickandmortymvp.ui.utils.pageCharacter

class EpisodeAdapter : RecyclerViewAdapter<String>() {

    var onClick: (Int) -> Unit = {}

    override val viewHolderLayoutId: Int = R.layout.item_number

    override fun bindModel(holder: ViewHolder, model: String) {
        with(holder.itemView) {

            numberView.text = model.pageCharacter().toString()

            setOnClickListener {
                onClick(
                    model.pageCharacter()
                )
            }

        }
    }

}