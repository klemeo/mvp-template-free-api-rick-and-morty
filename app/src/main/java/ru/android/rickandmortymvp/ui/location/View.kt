package ru.android.rickandmortymvp.ui.location

import ru.android.rickandmortymvp.app.models.data.location_pres_model.LocationPresModel

interface View {

    fun refreshLocation(location: LocationPresModel)

    fun showLocation(animated: Boolean)

}