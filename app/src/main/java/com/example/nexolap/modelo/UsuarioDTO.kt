package com.example.nexolap.modelo

data class UsuarioDTO(var id : Int, var nombre : String, var correo : String, var contraseña : String, var keepLogged : Boolean = false)
data class UsuarioDTORetroFit(var id : String, var nombre : String, var correo : String, var contraseña : String, var keepLogged : Boolean = false)
