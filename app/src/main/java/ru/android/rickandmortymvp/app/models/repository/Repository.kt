package ru.android.rickandmortymvp.app.models.repository

import io.reactivex.Single
import ru.android.rickandmortymvp.app.models.data.character.Character
import ru.android.rickandmortymvp.app.models.data.character.Characters
import ru.android.rickandmortymvp.app.models.data.episode.Episode
import ru.android.rickandmortymvp.app.models.data.episode.Episodes
import ru.android.rickandmortymvp.app.models.data.location.Location
import ru.android.rickandmortymvp.app.models.data.location.Locations

interface Repository {

    fun getCharacters(): Single<Characters>

    fun getCharacter(id: Int): Single<Character>

    fun getLocations(): Single<Locations>

    fun getLocation(id: Int): Single<Location>

    fun getEpisodes(): Single<Episodes>

    fun getEpisode(id: Int): Single<Episode>

}