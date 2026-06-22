package com.example.elvitralapp.data.model

import com.google.gson.annotations.SerializedName

data class Visita(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("address") val address: String,
    @SerializedName("service") val service: String,
    @SerializedName("date") val date: String,
    @SerializedName("time") val time: String,
    @SerializedName("description") val description: String,
    @SerializedName("status") val status: String = "Pendiente"
)
