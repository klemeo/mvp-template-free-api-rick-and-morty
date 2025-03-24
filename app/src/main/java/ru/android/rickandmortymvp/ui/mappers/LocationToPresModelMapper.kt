package ru.android.rickandmortymvp.ui.mappers

import ru.android.rickandmortymvp.app.models.data.location.Location
import ru.android.rickandmortymvp.app.models.data.location_pres_model.LocationPresModel

fun Location.toMap() = LocationPresModel(
    created = created,
    dimension = dimension,
    id = id,
    name = name,
    residents = residents,
    type = type,
    url = url
)