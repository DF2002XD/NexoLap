package com.example.nexolap.Data

/**
 * Representa una especificación técnica de un componente de un ordenador.
 *
 * @property nombre El nombre de la característica o componente (e.g., "Procesador", "Memoria RAM").
 * @property detalle El valor o descripción detallada de la característica (e.g., "Intel Core i7-11800H", "16GB DDR4").
 */
data class Especificacion(
    //val idEspecificacion : Int,
    val nombre : String,
    val detalle: String
)
