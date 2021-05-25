package ru.android.rickandmortymvp.ui.mappers

import org.koin.core.KoinComponent
import ru.android.rickandmortymvp.app.models.data.location.Location
import ru.android.rickandmortymvp.app.models.data.location_pres_model.LocationPresModel

class LocationToPresModelMapper : KoinComponent {

    fun map(from: Location) = LocationPresModel(
        created = from.created,
        dimension = from.dimension,
        id = from.id,
        name = from.name,
        residents = from.residents,
        type = from.type,
        url = from.url
    )

}