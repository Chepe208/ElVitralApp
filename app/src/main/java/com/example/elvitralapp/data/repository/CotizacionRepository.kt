package com.example.elvitralapp.data.repository

import com.example.elvitralapp.data.api.ApiService
import com.example.elvitralapp.data.model.Cotizacion

class CotizacionRepository(private val apiService: ApiService) {

    suspend fun getCotizaciones(): List<Cotizacion> =
        apiService.getCotizaciones()

    suspend fun createCotizacion(cotizacion: Cotizacion): Cotizacion =
        apiService.createCotizacion(cotizacion)
}
