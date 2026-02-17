package com.example.nexolap.viewmodel.uistate

data class ListaOrdenadorSpecsUIState(var listaOrdenadorSpecs: List<OrdenadorSpecsUIState> = ArrayList())
data class OrdenadorSpecsUIState( var id_Ordenador : String, var id_Especificacion : String)

data class  ListaOrdenadorSpecsUISrtate1(var listaOrdenadorSpecs: List<OrdenadorSpecsUIState1> = ArrayList())
data class OrdenadorSpecsUIState1(val id_Ordenador: Int, val id_Especificacion: Int)
