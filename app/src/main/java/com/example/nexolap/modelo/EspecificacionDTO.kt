package com.example.nexolap.modelo

/**
 * Data Transfer Object que representa las especificaciones técnicas de un equipo.
 *
 * @property id Identificador único de la especificación.
 * @property componente Nombre del componente técnico (ej. RAM, Procesador, Disco Duro).
 * @property descripcion Detalle o valor de la especificación técnica.
 */
data class EspecificacionDTO(var id: Int, var componente: String, var descripcion: String)

/**
 * Objeto de Transferencia de Datos (DTO) diseñado específicamente para las operaciones de red con Retrofit.
 *
 * Esta clase representa una especificación, utilizando el [id] como un [String] para mantener
 * la compatibilidad con el formato de datos de la API remota.
 *
 * @property id El identificador único de la especificación (en formato cadena).
 * @property componente El nombre o tipo de componente asociado a la especificación.
 * @property descripcion El detalle o descripción técnica de la especificación.
 */
data class EspecificacionDTORetroFit(
    var id: String,
    var componente: String,
    var descripcion: String
)


