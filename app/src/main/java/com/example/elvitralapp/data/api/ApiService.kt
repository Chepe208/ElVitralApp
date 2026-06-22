package com.example.elvitralapp.data.api

import com.example.elvitralapp.data.model.Producto
import com.example.elvitralapp.data.model.User
import com.example.elvitralapp.data.model.Cotizacion
import retrofit2.http.*

interface ApiService {
    // Producto CRUD
    @GET("producto")
    suspend fun getProducto(): List<Producto>

    @GET("producto/{id}")
    suspend fun getProducto(@Path("id") id: String): Producto

    @POST("producto")
    suspend fun createProducto(@Body item: Producto): Producto

    @PUT("producto/{id}")
    suspend fun updateProducto(@Path("id") id: String, @Body producto: Producto): Producto

    @DELETE("producto/{id}")
    suspend fun deleteProducto(@Path("id") id: String)

    // Proyecto CRUD
    @GET("proyecto")
    suspend fun getProyectos(): List<com.example.elvitralapp.data.model.Proyecto>

    // User CRUD
    @GET("user")
    suspend fun getUsers(): List<User>

    @GET("user/{id}")
    suspend fun getUser(@Path("id") id: String): User

    @POST("user")
    suspend fun createUser(@Body user: User): User

    @PUT("user/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: User): User

    @DELETE("user/{id}")
    suspend fun deleteUser(@Path("id") id: String)

    // Visita CRUD
    @GET("visita")
    suspend fun getVisitas(): List<com.example.elvitralapp.data.model.Visita>

    @GET("visita/{id}")
    suspend fun getVisita(@Path("id") id: String): com.example.elvitralapp.data.model.Visita

    @POST("visita")
    suspend fun createVisita(@Body visita: com.example.elvitralapp.data.model.Visita): com.example.elvitralapp.data.model.Visita

    @PUT("visita/{id}")
    suspend fun updateVisita(@Path("id") id: String, @Body visita: com.example.elvitralapp.data.model.Visita): com.example.elvitralapp.data.model.Visita

    @DELETE("visita/{id}")
    suspend fun deleteVisita(@Path("id") id: String)

    // Cotizacion CRUD - Regresamos al estándar plural para JSON Server
    @GET("cotizacion")
    suspend fun getCotizaciones(): List<Cotizacion>

    @POST("cotizacion")
    suspend fun createCotizacion(@Body cotizacion: Cotizacion): Cotizacion
}
