package com.example.elvitralapp.data.repository

import com.example.elvitralapp.data.api.ApiService
import com.example.elvitralapp.data.model.Visita

class VisitaRepository(private val apiService: ApiService) {
    suspend fun getVisitas() = apiService.getVisitas()
    suspend fun getVisita(id: String) = apiService.getVisita(id)
    suspend fun createVisita(visita: Visita) = apiService.createVisita(visita)
    suspend fun updateVisita(id: String, visita: Visita) = apiService.updateVisita(id, visita)
    suspend fun deleteVisita(id: String) = apiService.deleteVisita(id)
}
