package com.example.nexolap.modelo

/**
 * Modelo de datos unificado para los ordenadores.
 * Utiliza la estructura requerida por la API de Retrofit.
 *
 * @property id El identificador único del ordenador (en formato String para compatibilidad con la API).
 * @property nombre El nombre o modelo del ordenador.
 * @property imagenPrincipal La URL o identificador de la imagen principal del ordenador.
 * @property categorias Lista de categorías a las que pertenece el ordenador (ej: "Populares", "Más Vendidos").
 */
data class OrdenadorDTO(
    val id: String,
    val nombre: String,
    val imagenPrincipal: String,
    val categorias: List<String> = emptyList()
)
