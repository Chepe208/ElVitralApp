package com.example.elvitralapp.data.model

import com.google.gson.annotations.SerializedName

data class Producto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("avatar") val avatar: String? = null
)