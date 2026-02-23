package com.example.nexolap.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para una lista de especificaciones provenientes de la API.
 *
 * @property listaEspecificaciones Una lista de objetos [EspecificacionUIState] que contienen la información detallada de cada componente.
 *///API
data class ListaEspecificacionesUIState(var listaEspecificaciones: List<EspecificacionUIState> = ArrayList())
/**
 * Representa el estado de la interfaz de usuario para una especificación individual proveniente de la API.
 *
 * @property id Identificador único de la especificación.
 * @property componente Nombre o tipo del componente al que pertenece la especificación.
 * @property descripcion Detalle o explicación de las características del componente.
 */
data class EspecificacionUIState(var id : String, var componente : String, var descripcion : String)

/**
 * Representa el estado de la interfaz de usuario para una lista de especificaciones
 * de origen local.
 *
 * @property listaEspecificaciones Colección de especificaciones obtenidas localmente para ser mostradas en la vista.
 *///Local
data class ListaEspecificacionesUIState1(var listaEspecificaciones: List<especificacionUIState1> = ArrayList())
/**
 * Representa el estado de la interfaz de usuario para una especificación individual almacenada de forma local.
 *
 * @property id El identificador único numérico de la especificación.
 * @property componente El nombre o tipo del componente de hardware o software.
 * @property descripcion El detalle o valor técnico asociado al componente.
 */
data class especificacionUIState1(val id: Int, val componente: String, val descripcion: String)
