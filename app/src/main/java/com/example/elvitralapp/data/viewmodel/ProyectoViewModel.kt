package com.example.elvitralapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elvitralapp.data.model.Proyecto
import com.example.elvitralapp.data.repository.ProyectoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProyectoViewModel(private val repository: ProyectoRepository) : ViewModel() {

    private val _proyectos = MutableStateFlow<List<Proyecto>>(emptyList())
    val proyectos: StateFlow<List<Proyecto>> = _proyectos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchProyectos()
    }

    fun fetchProyectos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _proyectos.value = repository.getProyectos()
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al cargar proyectos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
