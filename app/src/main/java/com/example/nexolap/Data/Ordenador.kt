package com.example.nexolap.data

/**
 * Representación de un ordenador tal como se recibe desde una API externa mediante Retrofit.
 * Esta clase se utiliza para el mapeo de datos JSON a objetos de Kotlin.
 *
 * @property id Identificador único del ordenador proporcionado por la API (en formato String).
 * @property nombre El nombre o modelo del ordenador.
 * @property imagenPrincipal URL o identificador de la imagen principal del ordenador.
 */
data class OrdenadorRetrofit(
    val id: String,
    val nombre: String,
    val imagenPrincipal: String
)
