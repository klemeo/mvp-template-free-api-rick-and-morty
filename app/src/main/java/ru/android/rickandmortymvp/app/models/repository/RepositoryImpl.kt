package ru.android.rickandmortymvp.app.models.repository

import io.reactivex.Single
import ru.android.rickandmortymvp.api.RickAndMortyApi
import ru.android.rickandmortymvp.app.models.data.character.Character
import ru.android.rickandmortymvp.app.models.data.character.Characters
import ru.android.rickandmortymvp.app.models.data.episode.Episode
import ru.android.rickandmortymvp.app.models.data.episode.Episodes
import ru.android.rickandmortymvp.app.models.data.location.Location
import ru.android.rickandmortymvp.app.models.data.location.Locations

class RepositoryImpl(
    private val api: RickAndMortyApi
) : Repository {

    override fun getCharacters(page: Int?): Single<Characters> =
        api.getCharacters(page).flatMap { response ->
            if (response.results != null) {
                Single.just(response)
            } else {
                Single.error(Throwable("Request failed"))
            }
        }

    override fun getCharacter(id: Int): Single<Character> =
        api.getCharacter(id).flatMap { response ->
            if (response.id != null) {
                Single.just(response)
            } else {
                Single.error(Throwable("Request failed"))
            }
        }

    override fun getLocations(): Single<Locations> =
        api.getLocations().flatMap { response ->
            if (response.results != null) {
                Single.just(response)
            } else {
                Single.error(Throwable("Request failed"))
            }
        }

    override fun getLocation(id: Int): Single<Location> =
        api.getLocation(id).flatMap { response ->
            if (response.id != null) {
                Single.just(response)
            } else {
                Single.error(Throwable("Request failed"))
            }
        }

    override fun getEpisodes(): Single<Episodes> =
        api.getEpisodes().flatMap { response ->
            if (response.results != null) {
                Single.just(response)
            } else {
                Single.error(Throwable("Request failed"))
            }
        }

    override fun getEpisode(id: Int): Single<Episode> =
        api.getEpisode(id).flatMap { response ->
            if (response.id != null) {
                Single.just(response)
            } else {
                Single.error(Throwable("Request failed"))
            }
        }

}