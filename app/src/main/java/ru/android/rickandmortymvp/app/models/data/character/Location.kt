package ru.android.rickandmortymvp.app.models.data.character


import com.google.gson.annotations.SerializedName

class Location(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)