package ru.android.rickandmortymvp.ui.locations

import kotlinx.android.synthetic.main.item_episode.view.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.location_pres_model.LocationPresModel
import ru.android.rickandmortymvp.base.RecyclerViewAdapter

class LocationsAdapter : RecyclerViewAdapter<LocationPresModel>() {

    var onClick: (LocationPresModel) -> Unit = {}

    override val viewHolderLayoutId: Int = R.layout.item_location

    override fun bindModel(holder: ViewHolder, model: LocationPresModel) {
        with(holder.itemView) {

            textName.text = model.name

            setOnClickListener { onClick(model) }

        }
    }
}