package com.example.elvitralapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elvitralapp.data.repository.ProductoRepository
import com.example.elvitralapp.data.repository.ProyectoRepository
import com.example.elvitralapp.data.repository.VisitaRepository

class ViewModelFactory(private val repository: Any) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ProductoViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                ProductoViewModel(repository as ProductoRepository) as T
            }
            modelClass.isAssignableFrom(ProyectoViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                ProyectoViewModel(repository as ProyectoRepository) as T
            }
            modelClass.isAssignableFrom(VisitaViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                VisitaViewModel(repository as VisitaRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
