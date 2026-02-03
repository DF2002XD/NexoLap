package com.example.nexolap.viewmodel.uistate

//API
data class ListaEspecificacionesUIState(var listaEspecificaciones: List<EspecificacionUIState> = ArrayList())
data class EspecificacionUIState(var id : String, var componente : String, var descripcion : String)

//Local
data class ListaEspecificacionesUIState1(var listaEspecificaciones: List<especificacionUIState1> = ArrayList())
data class especificacionUIState1(val id: Int, val componente: String, val descripcion: String)
