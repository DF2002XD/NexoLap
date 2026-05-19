package com.example.nexolap.modelo

/**
 * Modelo de datos unificado para la relación entre un ordenador y sus especificaciones.
 *
 * @property id_Ordenador El identificador único del ordenador.
 * @property id_Especificacion El identificador único de la especificación.
 */
data class OrdenadorSpecsDTO(
    val id_Ordenador: String,
    val id_Especificacion: String
)
