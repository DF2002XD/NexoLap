package com.example.nexolap.Data

/**
 * Representa un modelo de ordenador con sus características principales.
 *
 * @property id El identificador único del ordenador.
 * @property imagenPrincipal El ID del recurso drawable para la imagen principal del ordenador.
 * @property nombre El nombre o modelo del ordenador.
 * @property especificaciones Una lista de objetos [Especificacion] que detallan las características técnicas del ordenador.
 */
data class Ordenador(
    val id: Int,
    val imagenPrincipal: Int,
    val nombre: String
)


