package com.example.nexolap.viewmodel.uistate

/**
 * Estado de la interfaz de usuario para la pantalla de añadir ordenador.
 *
 * @property nombre El nombre del ordenador que se está ingresando.
 * @property imagenUrl La URL de la imagen del ordenador.
 * @property especificaciones Lista de pares (Componente, Descripción) para los detalles técnicos.
 * @property categorias Lista de categorías seleccionadas.
 * @property isLoading Indica si se está realizando una operación de guardado.
 * @property success Indica si la operación de guardado fue exitosa.
 * @property error Mensaje de error en caso de que falle el guardado.
 */
data class AddOrdenadorUIState(
    val nombre: String = "",
    val imagenUrl: String = "",
    val especificaciones: List<Pair<String, String>> = listOf("" to ""),
    val categorias: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)
