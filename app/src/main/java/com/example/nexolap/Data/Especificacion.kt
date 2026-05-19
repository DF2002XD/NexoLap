package com.example.nexolap.data

/**
 * Representa los detalles técnicos o especificaciones de un componente de hardware
 * recibidos desde la API de NexoLap.
 *
 * @property id El identificador único de la especificación.
 * @property componente El nombre o categoría del componente (ej. Procesador, RAM).
 * @property descripcion El detalle o valor específico de dicha característica.
 */
data class EspecificacionRetrofit(
    val id: String,
    val componente: String,
    val descripcion: String
)


