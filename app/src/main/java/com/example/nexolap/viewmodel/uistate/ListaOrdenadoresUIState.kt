package com.example.nexolap.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para la pantalla de listado de ordenadores.
 *
 * @property listaOrdenadores Lista de objetos [OrdenadorUIState] que contienen la información básica
 * de los ordenadores a mostrar (procedentes de la API).
 * @property isLoading Indica si los datos se están cargando actualmente desde la fuente de datos.
 * @property error Mensaje de error opcional en caso de fallo en la carga.
 */
data class ListaOrdenadoresUIState(
    val listaOrdenadores: List<OrdenadorUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * Representa el estado de la interfaz de usuario para un ordenador individual obtenido de la API.
 *
 * @property id El identificador único del ordenador.
 * @property nombre El nombre descriptivo del ordenador.
 * @property imagenPrincipal La URL o ruta de la imagen principal del ordenador.
 */
data class OrdenadorUIState(
    var id: String, 
    var nombre: String, 
    var imagenPrincipal: String,
    val categorias: List<String> = emptyList()
)
