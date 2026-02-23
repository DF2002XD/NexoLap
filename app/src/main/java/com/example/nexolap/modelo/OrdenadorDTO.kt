package com.example.nexolap.modelo

/**
 * Objeto de Transferencia de Datos (DTO) que representa la información básica de un ordenador.
 *
 * @property id El identificador único del ordenador.
 * @property nombre El nombre o modelo del ordenador.
 * @property imagenPrincipal El identificador del recurso (ID) de la imagen principal asociada al ordenador.
 */
data class OrdenadorDTO(var id: Int, var nombre: String, var imagenPrincipal: Int)

/**
 * Objeto de Transferencia de Datos (DTO) utilizado para representar un ordenador
 * en las peticiones y respuestas de la API a través de Retrofit.
 *
 * @property id El identificador único del ordenador en formato String.
 * @property nombre El nombre o modelo del ordenador.
 * @property imagenPrincipal La URL o ruta de la imagen principal del ordenador.
 */
data class OrdenadorDTORetroFit(var id: String, var nombre: String, var imagenPrincipal: String)