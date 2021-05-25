package ru.android.rickandmortymvp.app.models.data.character


import com.google.gson.annotations.SerializedName

class Origin(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)