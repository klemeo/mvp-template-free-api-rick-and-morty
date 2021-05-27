package ru.android.rickandmortymvp.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.android.rickandmortymvp.app.models.data.character.Character
import ru.android.rickandmortymvp.app.models.data.character.Characters
import ru.android.rickandmortymvp.app.models.data.episode.Episode
import ru.android.rickandmortymvp.app.models.data.episode.Episodes
import ru.android.rickandmortymvp.app.models.data.location.Location
import ru.android.rickandmortymvp.app.models.data.location.Locations

interface RickAndMortyApi {

    @GET("character")
    fun getCharacters(
        @Query("page") page: Int?
    ): Single<Characters>

    @GET("character/{id}")
    fun getCharacter(
        @Path("id") id: Int
    ): Single<Character>

    @GET("location")
    fun getLocations(
        @Query("page") page: Int?
    ): Single<Locations>

    @GET("location/{id}")
    fun getLocation(
        @Path("id") id: Int
    ): Single<Location>

    @GET("episode")
    fun getEpisodes(
        @Query("page") page: Int?
    ): Single<Episodes>

    @GET("episode/{id}")
    fun getEpisode(
        @Path("id") id: Int
    ): Single<Episode>

}