package com.example.nexolap.modelo

data class UsuarioDTO(
    var id: Int,
    var nombre: String,
    var correo: String,
    var contraseña: String,
    var keepLogged: Boolean = false,
    var color: Int = 0 // 0 significa que no tiene color asignado aún
)

data class UsuarioDTORetroFit(
    var id: String,
    var nombre: String,
    var correo: String,
    var contraseña: String,
    var keepLogged: Boolean = false,
    var color: Int = 0
)
