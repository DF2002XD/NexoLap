package com.example.nexolap.Data.repository

import com.example.nexolap.modelo.UsuarioDTO

/**
 * Interfaz del repositorio que define las operaciones de acceso a datos para la entidad Usuario.
 * Proporciona métodos para la gestión CRUD y el manejo del estado de sesión del usuario.
 *
 * @property readAll Obtiene la lista completa de usuarios.
 * @property read Busca un usuario específico por su identificador único.
 * @property create Registra un nuevo usuario en el sistema.
 * @property update Actualiza la información de un usuario existente.
 * @property delete Elimina un usuario del sistema mediante su ID.
 * @property loginUser Autentica a un usuario mediante sus credenciales y gestiona la persistencia de la sesión.
 * @property loggoutUSer Cierra la sesión activa del usuario actual.
 * @property getCurrentUser Recupera el usuario que tiene la sesión iniciada actualmente.
 */
interface IUsuarioRepo {
    fun readAll(onSucess: (List<UsuarioDTO>) -> Unit, onError: () -> Unit)
    fun read(id: Int, onSucess: (usuarioCreado: UsuarioDTO?) -> Unit, onError: () -> Unit)

    fun create(usuarioDTO: UsuarioDTO, onSucess: () -> Unit, onError: () -> Unit)

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
