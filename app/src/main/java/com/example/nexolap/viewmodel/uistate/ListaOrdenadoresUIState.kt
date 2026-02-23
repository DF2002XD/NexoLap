package com.example.nexolap.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para la pantalla de listado de ordenadores.
 *
 * @property listaOrdenadores Lista de objetos [OrdenadorUIState] que contienen la información básica
 * de los ordenadores a mostrar (procedentes de la API).
 *///API
data class ListaOrdenadoresUIState(var listaOrdenadores: List<OrdenadorUIState> = ArrayList())
/**
 * Representa el estado de la interfaz de usuario para un ordenador individual obtenido de la API.
 *
 * @property id El identificador único del ordenador.
 * @property nombre El nombre descriptivo del ordenador.
 * @property imagenPrincipal La URL o ruta de la imagen principal del ordenador.
 */
data class OrdenadorUIState(var id : String, var nombre : String, var imagenPrincipal : String)

/**
 * Estado de la interfaz de usuario que representa una lista de ordenadores utilizando recursos locales.
 *
 * @property listaOrdenadores Lista de objetos [OrdenadorUIState1] que contienen la información local de los equipos.
 *///Local
data class ListaOrdenadoresUIState1(var listaOrdenadores: List<OrdenadorUIState1> = ArrayList())
/**
 * Representa el estado de la interfaz de usuario para un ordenador individual cargado desde una fuente local.
 *
 * @property id El identificador único del ordenador.
 * @property nombre El nombre o modelo del ordenador.
 * @property imagenPrincipal El identificador de recurso (ID) de la imagen principal del ordenador.
 */
data class OrdenadorUIState1(val id: Int, val nombre: String, val imagenPrincipal: Int)
