package com.example.nexolap.data.retrofit.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto singleton que proporciona la configuración de red y la instancia del servicio API para la aplicación.
 *
 * Este módulo se encarga de inicializar y configurar:
 * - [OkHttpClient] con un interceptor de registros ([HttpLoggingInterceptor]) para depurar las peticiones y respuestas.
 * - [Retrofit] con un convertidor Gson para la serialización y deserialización de datos JSON.
 * - La implementación de [NexoLapApiService] para realizar las llamadas a la API.
 *
 * La URL base está configurada para apuntar al servidor de desarrollo local (10.0.2.2).
 */
object NetworkModule {
    const val BASE_URL = "http://10.0.2.2:5131/"

    fun getImageUrl(id: String): String = "${BASE_URL}images/$id"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: NexoLapApiService = retrofit.create(NexoLapApiService::class.java)
}
