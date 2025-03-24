package ru.android.rickandmortymvp.ui.mappers

import ru.android.rickandmortymvp.app.models.data.location.Locations
import ru.android.rickandmortymvp.app.models.data.location_pres_model.*

fun Locations.toMap() = LocationsPresModel(
        info = info.let { info ->
            InfoPresModel(
                count = info?.count,
                next = info?.next,
                pages = info?.pages,
                prev = info?.prev,
            )
        },
        results = results?.map { result ->
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