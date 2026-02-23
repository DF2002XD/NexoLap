package com.example.nexolap.Data.retrofit.repo

import com.example.nexolap.modelo.UsuarioDTORetroFit

/**
 * Interfaz que define el repositorio de operaciones para la gestión de usuarios mediante una API.
 *
 * Proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar),
 * así como la gestión de autenticación y persistencia de sesiones de usuario.
 */
interface IUsuarioApiRepo {
    fun readAll(onSucess: (List<UsuarioDTORetroFit>) -> Unit, onError: () -> Unit)
    fun read(
        id: String,
        onSucess: (usuarioCreado: UsuarioDTORetroFit?) -> Unit,
        onError: () -> Unit
    )

    fun create(usuarioDTO: UsuarioDTORetroFit, onSucess: () -> Unit, onError: () -> Unit)

    fun update(usuarioDTO: UsuarioDTORetroFit, onSucess: () -> Unit, onError: () -> Unit)
    fun delete(id: String, onSucess: () -> Unit, onError: () -> Unit)

    fun loginUser(
        correo: String,
        contrasenha: String,
        keepLogged: Boolean,
        onSucess: (UsuarioDTORetroFit) -> Unit,
        onError: () -> Unit
    )

    fun loggoutUSer(onSucess: () -> Unit, onError: () -> Unit)

    fun getCurrentUser(): UsuarioDTORetroFit?

    fun checkStoredSession(onSucess: (UsuarioDTORetroFit) -> Unit, onError: () -> Unit)
}
