package ru.android.rickandmortymvp.ui.mappers

import org.koin.core.KoinComponent
import ru.android.rickandmortymvp.app.models.data.location.Locations
import ru.android.rickandmortymvp.app.models.data.location_pres_model.*

class LocationsToPresModelMapper : KoinComponent {

    fun map(from: Locations) = LocationsPresModel(
        info = from.info.let { info ->
            InfoPresModel(
                count = info?.count,
                next = info?.next,
                pages = info?.pages,
                prev = info?.prev,
            )
        },
        results = from.results?.map { result ->
            LocationPresModel(
                created = result.created,
                dimension = result.dimension,
                id = result.id,
                name = result.name,
                residents = result.residents,
                type = result.type,
                url = result.url
            )
        }
    )

}