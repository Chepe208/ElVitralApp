package com.example.elvitralapp.data.model

import com.google.gson.annotations.SerializedName

data class Cotizacion(
    @SerializedName("id") val id: String? = null,
    @SerializedName("nombreCliente") val nombreCliente: String,
    @SerializedName("email") val email: String,
    @SerializedName("telefono") val telefono: String,
    @SerializedName("direccion") val direccion: String,
    @SerializedName("productos") val productos: List<ProductoCotizado>,
    @SerializedName("total") val total: Double
)

data class ProductoCotizado(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("largo") val largo: Double,
    @SerializedName("ancho") val ancho: Double,
    @SerializedName("cantidad") val cantidad: Int,
    @SerializedName("precioUnitario") val precioUnitario: Double
)
