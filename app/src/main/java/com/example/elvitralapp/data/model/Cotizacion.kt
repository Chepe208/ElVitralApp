package com.example.elvitralapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Modelo ultra-simplificado para evitar el Error 500.
 * Enviamos los productos como un String (JSON) para que el servidor no tenga que procesar objetos anidados,
 * que es lo que suele causar el fallo en JSON Server / Render.
 */
data class Cotizacion(
    @SerializedName("id") val id: String? = null,
    @SerializedName("nombreCliente") val nombreCliente: String,
    @SerializedName("email") val email: String,
    @SerializedName("telefono") val telefono: String,
    @SerializedName("direccion") val direccion: String,
    @SerializedName("productosJson") val productosJson: String,
    @SerializedName("total") val total: Int
)

/** Envoltorio que refleja la estructura anidada del JSON Server. */
data class CotizacionWrapper(
    @SerializedName("cotizacion") val cotizacion: List<Cotizacion> = emptyList()
)

data class ProductoCotizado(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("largo") val largo: Double,
    @SerializedName("ancho") val ancho: Double,
    @SerializedName("cantidad") val cantidad: Int,
    @SerializedName("precioUnitario") val precioUnitario: Double
)
