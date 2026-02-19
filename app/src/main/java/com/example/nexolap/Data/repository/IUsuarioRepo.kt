package com.example.nexolap.Data.repository

import com.example.nexolap.modelo.UsuarioDTO

interface IUsuarioRepo {
    fun readAll(onSucess: (List<UsuarioDTO>) -> Unit, onError: () -> Unit)
    fun read(id: Int, onSucess: (usuarioCreado: UsuarioDTO?) -> Unit, onError: () -> Unit)
    fun update(usuarioDTO: UsuarioDTO, onSucess: () -> Unit, onError: () -> Unit)
    fun delete(id: Int, onSucess: () -> Unit, onError: () -> Unit)

    fun loginUser(
        correo: String,
        contrasenha: String,
        keepLogged: Boolean,
        onSucess: (UsuarioDTO) -> Unit,
        onError: () -> Unit
    )

    fun loggoutUSer(onSucess: () -> Unit, onError: () -> Unit)

    fun getCurrentUser(): UsuarioDTO?
}
