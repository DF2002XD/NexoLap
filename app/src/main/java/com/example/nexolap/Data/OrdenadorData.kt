package com.example.nexolap.Data

data class Ordenador(
    val id: Int,
    val imagenPrincipal: Int,
    val nombre: String,
    val especificaciones: List<Especificacion>
)

data class Especificacion(
    val nombre: String,val detalle: String
)