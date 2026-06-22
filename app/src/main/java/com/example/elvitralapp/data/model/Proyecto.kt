package com.example.elvitralapp.data.model

import com.google.gson.annotations.SerializedName

data class Proyecto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("imagen") val image: String? = null
)
