package ru.android.rickandmortymvp.app.models.data.character


import com.google.gson.annotations.SerializedName

class Characters(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Character>?
)