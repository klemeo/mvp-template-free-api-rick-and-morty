package ru.android.rickandmortymvp.app.models.data.episode


import com.google.gson.annotations.SerializedName

class Episodes(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Episode>?
)