package com.example.nexolap.viewmodel.uistate

//API
data class ListaOrdenadoresUIState(var listaOrdenadores: List<OrdenadorUIState> = ArrayList())
data class OrdenadorUIState(var id : String, var nombre : String, var imagenPrincipal : String)

//Local
data class ListaOrdenadoresUIState1(var listaOrdenadores: List<OrdenadorUIState1> = ArrayList())
data class OrdenadorUIState1(val id: Int, val nombre: String, val imagenPrincipal: Int)

