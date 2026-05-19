package com.example.nexolap.modelo

import com.google.gson.annotations.SerializedName

/**
 * Modelo de datos unificado para las especificaciones técnicas.
 * Utiliza la estructura requerida por la API de Retrofit.
 *
 * @property id Identificador único de la especificación.
 * @property componente Nombre del componente técnico (ej. RAM, Procesador, Disco Duro).
 * @property descripcion Detalle o valor de la especificación técnica.
 */
data class EspecificacionDTO(
    val id: String,
    val componente: String,
    val descripcion: String
)
