package com.example.elvitralapp.data.repository
import com.example.elvitralapp.data.api.ApiService
import com.example.elvitralapp.data.model.User

class UserRepository(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
    suspend fun getUser(id: String) = apiService.getUser(id)
    suspend fun createUser(user: User) = apiService.createUser(user)
    suspend fun updateUser(id: String, user: User) = apiService.updateUser(id, user)
    suspend fun deleteUser(id: String) = apiService.deleteUser(id)
}