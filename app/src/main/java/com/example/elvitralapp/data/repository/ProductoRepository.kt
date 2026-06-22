package com.example.elvitralapp.data.repository
import com.example.elvitralapp.data.api.ApiService
import com.example.elvitralapp.data.model.Producto

class ProductoRepository(private val apiService: ApiService) {
    suspend fun getProducto() = apiService.getProducto()
    suspend fun getProducto(id: String) = apiService.getProducto(id)
    suspend fun createProducto(producto: Producto) = apiService.createProducto(producto)
    suspend fun updateProducto(id: String, producto: Producto) = apiService.updateProducto(id, producto)
    suspend fun deleteProducto(id: String) = apiService.deleteProducto(id)
}