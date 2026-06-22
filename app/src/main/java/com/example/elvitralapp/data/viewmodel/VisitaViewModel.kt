package com.example.elvitralapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elvitralapp.data.model.Visita
import com.example.elvitralapp.data.repository.VisitaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VisitaViewModel(private val repository: VisitaRepository) : ViewModel() {

    private val _visitas = MutableStateFlow<List<Visita>>(emptyList())
    val visitas: StateFlow<List<Visita>> = _visitas

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchVisitas()
    }

    fun fetchVisitas() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _visitas.value = repository.getVisitas()
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al cargar visitas: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createVisita(visita: Visita, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.createVisita(visita)
                fetchVisitas()
                onSuccess()
            } catch (e: Exception) {
                _error.value = "Error al crear visita: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateVisita(id: String, visita: Visita) {
        viewModelScope.launch {
            try {
                repository.updateVisita(id, visita)
                fetchVisitas()
            } catch (e: Exception) {
                _error.value = "Error al actualizar visita: ${e.message}"
            }
        }
    }

    fun deleteVisita(id: String) {
        viewModelScope.launch {
            try {
                repository.deleteVisita(id)
                fetchVisitas()
            } catch (e: Exception) {
                _error.value = "Error al eliminar visita: ${e.message}"
            }
        }
    }
}
