package com.example.nexolap.Data.repository

import com.example.nexolap.Data.Usuario
import com.example.nexolap.modelo.UsuarioDTO

class UsuarioRepo : IUsuarioRepo {

    companion object {
        var usuario = ArrayList(
            listOf(
                UsuarioDTO(id = 0, nombre = "Admin", correo = "admin@gmail.com", contraseña = "admin"),
                UsuarioDTO(id = 1, nombre = "Usuario", correo = "usuario@gmail.com", contraseña = "usuario")
            )
        )
    }


    override fun readAll(onSucess: (List<UsuarioDTO>) -> Unit, onError: () -> Unit) {
        onSucess(usuario)
    }

    override fun read(
        id: Int,
        onSucess: (usuarioCreado: UsuarioDTO?) -> Unit,
        onError: () -> Unit
    ) {
        onSucess(usuario.find { it.id == id })
    }

}