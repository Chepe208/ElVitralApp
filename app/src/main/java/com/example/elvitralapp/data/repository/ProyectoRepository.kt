package com.example.elvitralapp.data.repository

import com.example.elvitralapp.data.api.ApiService
import com.example.elvitralapp.data.model.Proyecto

class ProyectoRepository(private val apiService: ApiService) {
    suspend fun getProyectos(): List<Proyecto> = apiService.getProyectos()
}
