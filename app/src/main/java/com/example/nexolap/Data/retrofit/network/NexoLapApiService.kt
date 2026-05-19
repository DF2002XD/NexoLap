package com.example.nexolap.data.retrofit.network

import com.example.nexolap.modelo.UsuarioDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Interfaz de servicio Retrofit que define los puntos de conexión (endpoints) de la API para NexoLap.
 */
interface NexoLapApiService {
    @POST("auth/login")
    suspend fun login(@Body usuario: UsuarioDTO): Response<UsuarioDTO>

    @POST("auth/register")
    suspend fun register(@Body usuario: UsuarioDTO): Response<UsuarioDTO>

    @GET("json/{carpeta}")
    suspend fun getAll(@Path("carpeta") carpeta: String): Response<List<Any>>

    @GET("json/{carpeta}/{id}")
    suspend fun getById(
        @Path("carpeta") carpeta: String,
        @Path("id") id: String
    ): Response<Any>

    @POST("json/{carpeta}/{id}")
    suspend fun insert(
        @Path("carpeta") carpeta: String,
        @Path("id") id: String,
        @Body item: Any
    ): Response<Any>

    @PUT("json/{carpeta}/{id}")
    suspend fun updateById(
        @Path("carpeta") carpeta: String,
        @Path("id") id: String,
        @Body item: Any
    ): Response<Any>

    @PUT("json/{carpeta}")
    suspend fun update(
        @Path("carpeta") carpeta: String,
        @Body item: Any
    ): Response<Any>

    @DELETE("json/{carpeta}/{id}")
    suspend fun delete(
        @Path("carpeta") carpeta: String,
        @Path("id") id: String
    ): Response<Unit>

}
