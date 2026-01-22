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
    val nombre: String,
    val especificaciones: List<Especificacion>
)

/**
 * Representa una especificación técnica de un componente de un ordenador.
 *
 * @property nombre El nombre de la característica o componente (e.g., "Procesador", "Memoria RAM").
 * @property detalle El valor o descripción detallada de la característica (e.g., "Intel Core i7-11800H", "16GB DDR4").
 */
data class Especificacion(
    val nombre: String,val detalle: String
)