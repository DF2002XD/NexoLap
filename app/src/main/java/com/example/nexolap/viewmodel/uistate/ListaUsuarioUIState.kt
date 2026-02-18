package com.example.nexolap.viewmodel.uistate

data class ListaUsuarioUIState(var listaUsuarios: List<UsuarioUIState> = ArrayList())
data class UsuarioUIState(var id : String, var nombre : String, var correo : String, var contraseña : String)

data class  ListaUsuarioUIState1(var listaUsuarios: List<UsuarioUIState1> = ArrayList())
data class UsuarioUIState1(val id: Int, val nombre: String, val correo: String, val contraseña: String)


