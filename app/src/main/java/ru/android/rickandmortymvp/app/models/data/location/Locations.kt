package ru.android.rickandmortymvp.app.models.data.location


import com.google.gson.annotations.SerializedName

class Locations(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Location>?
)