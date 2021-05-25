package ru.android.rickandmortymvp.ui.locations

import ru.android.rickandmortymvp.app.models.data.location_pres_model.LocationsPresModel

interface View {

    fun refreshLocations(locations: LocationsPresModel)

    fun showLocations(animated: Boolean)

}