package com.example.nexolap.Data


data class Usuario(
    val UsuarioId: Int,
    val UsuarioNombre: String,
    val UsuarioCorreo: String,
    val UsuarioContrasenha: String,
    val UsuarioKeepLogged: Boolean,
    val UsuarioColor: Int
)
