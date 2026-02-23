package com.example.nexolap.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para una lista de especificaciones de ordenadores.
 *
 * @property listaOrdenadorSpecs Una lista de objetos [OrdenadorSpecsUIState] que contienen los identificadores
 * de la relación entre ordenadores y sus especificaciones técnicas.
 */
data class ListaOrdenadorSpecsUIState(var listaOrdenadorSpecs: List<OrdenadorSpecsUIState> = ArrayList())
data class OrdenadorSpecsUIState( var id_Ordenador : String, var id_Especificacion : String)

/**
 * Representa el estado de la interfaz de usuario para una lista de relaciones entre ordenadores y especificaciones.
 *
 * Esta versión utiliza identificadores de tipo numérico ([Int]) a través del modelo [OrdenadorSpecsUIState1].
 *
 * @property listaOrdenadorSpecs Lista que contiene las asociaciones entre los identificadores de ordenadores y especificaciones.
 */
data class  ListaOrdenadorSpecsUIState1(var listaOrdenadorSpecs: List<OrdenadorSpecsUIState1> = ArrayList())
data class OrdenadorSpecsUIState1(val id_Ordenador: Int, val id_Especificacion: Int)
