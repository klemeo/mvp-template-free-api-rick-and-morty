package ru.android.rickandmortymvp.app.models.data.character


import com.google.gson.annotations.SerializedName

class Info(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("prev")
    val prev: Any?
)