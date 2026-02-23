package com.example.nexolap.Data

/**
 * Representa la entidad de un ordenador para su visualización en la interfaz de usuario.
 *
 * @property id El identificador único del ordenador.
 * @property imagenPrincipal El identificador del recurso (ID de recurso de Android) para la imagen del ordenador.
 * @property nombre El nombre o modelo del ordenador.
 */
data class Ordenador(
    val id: Int,
    val imagenPrincipal: Int,
    val nombre: String
)

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
