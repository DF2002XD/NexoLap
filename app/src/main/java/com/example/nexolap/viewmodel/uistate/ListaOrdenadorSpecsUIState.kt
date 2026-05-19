package com.example.nexolap.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para una lista de especificaciones de ordenadores.
 *
 * @property listaOrdenadorSpecs Una lista de objetos [OrdenadorSpecsUIState] que contienen los identificadores
 * de la relación entre ordenadores y sus especificaciones técnicas.
 * @property isLoading Indica si los datos se están cargando actualmente.
 * @property error Mensaje de error en caso de fallo en la carga.
 */
data class ListaOrdenadorSpecsUIState(
    val listaOrdenadorSpecs: List<OrdenadorSpecsUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * Representa la relación entre un ordenador y una especificación técnica.
 *
 * @property id_Ordenador Identificador único del ordenador.
 * @property id_Especificacion Identificador único de la especificación técnica.
 */
data class OrdenadorSpecsUIState(var id_Ordenador: String, var id_Especificacion: String)
