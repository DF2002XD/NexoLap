package com.example.nexolap.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para una lista de especificaciones provenientes de la API.
 *
 * @property listaEspecificaciones Una lista de objetos [EspecificacionUIState] que contienen la información detallada de cada componente.
 * @property isLoading Indica si los datos se están cargando actualmente.
 * @property error Mensaje de error en caso de fallo en la carga.
 */
data class ListaEspecificacionesUIState(
    val listaEspecificaciones: List<EspecificacionUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * Representa el estado de la interfaz de usuario para una especificación individual proveniente de la API.
 *
 * @property id Identificador único de la especificación.
 * @property componente Nombre o tipo del componente al que pertenece la especificación.
 * @property descripcion Detalle o explicación de las características del componente.
 */
data class EspecificacionUIState(var id: String, var componente: String, var descripcion: String)
