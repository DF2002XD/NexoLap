package com.example.nexolap.Data

/**
 * Representa una especificación técnica de un producto o componente.
 *
 * @property id Identificador único de la especificación.
 * @property componente El nombre del componente o categoría técnica (ej. RAM, Procesador).
 * @property descripcion El detalle o valor específico del componente.
 */
data class Especificacion(
    val id: Int,
    val componente: String,
    val descripcion: String
)

/**
 * Representa los detalles técnicos o especificaciones de un componente de hardware
 * recibidos desde la API de NexoLap.
 *
 * @property id El identificador único de la especificación.
 * @property componente El nombre o categoría del componente (ej. Procesador, RAM).
 * @property descripcion El detalle o valor específico de dicha característica.
 */
data class especificacionRetrofit(
    val id: String,
    val componente: String,
    val descripcion: String
)


