package com.example.elvitralapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elvitralapp.data.model.Producto
import com.example.elvitralapp.data.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoViewModel(private val repository: ProductoRepository) : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchProductos()
    }

    fun fetchProductos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _productos.value = repository.getProducto()
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al cargar productos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteProducto(id: String) {
        viewModelScope.launch {
            try {
                repository.deleteProducto(id)
                fetchProductos() // Refresh list
            } catch (e: Exception) {
                _error.value = "Error al eliminar producto: ${e.message}"
            }
        }
    }
    
    fun createProducto(producto: Producto) {
        viewModelScope.launch {
            try {
                repository.createProducto(producto)
                fetchProductos() // Refresh list
            } catch (e: Exception) {
                _error.value = "Error al crear producto: ${e.message}"
            }
        }
    }

    fun updateProducto(id: String, producto: Producto) {
        viewModelScope.launch {
            try {
                repository.updateProducto(id, producto)
                fetchProductos() // Refresh list
            } catch (e: Exception) {
                _error.value = "Error al actualizar producto: ${e.message}"
            }
        }
    }
}
