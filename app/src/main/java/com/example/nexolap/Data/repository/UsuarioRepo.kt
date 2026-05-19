package com.example.nexolap.data.repository

import android.content.Context
import com.example.nexolap.modelo.UsuarioDTO
import java.util.UUID

/**
 * Repositorio encargado de la gestión de usuarios y el manejo de sesiones en la aplicación.
 */
class UsuarioRepo(context: Context) : IUsuarioRepo {

    private val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    companion object {
        var usuario = ArrayList(
            listOf(
                UsuarioDTO(
                    id = "0",
                    name = "Admin",
                    email = "admin@gmail.com",
                    passwd = "admin"
                ),
                UsuarioDTO(
                    id = "1",
                    name = "Usuario",
                    email = "usuario@gmail.com",
                    passwd = "usuario"
                )
            )
        )
        var currentUser: UsuarioDTO? = null

        @Volatile
        private var INSTANCE: UsuarioRepo? = null

        fun init(context: Context) {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = UsuarioRepo(context.applicationContext)
                    }
                }
            }
        }

        fun getInstance(): UsuarioRepo {
            return INSTANCE
                ?: throw IllegalStateException("UsuarioRepo no inicializado. Llama a UsuarioRepo.init(context) en tu Activity.")
        }
    }

    override fun readAll(onSuccess: (List<UsuarioDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(usuario)
    }

    override fun read(
        id: String,
        onSuccess: (usuarioCreado: UsuarioDTO?) -> Unit,
        onError: () -> Unit
    ) {
        onSuccess(usuario.find { it.id == id })
    }

    override fun create(
        usuarioDTO: UsuarioDTO,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val nextId = UUID.randomUUID().toString()
        val userWithId = usuarioDTO.copy(id = nextId)
        if (usuario.add(userWithId)) {
            onSuccess()
        } else {
            onError()
        }
    }

    override fun update(usuarioDTO: UsuarioDTO, onSuccess: () -> Unit, onError: () -> Unit) {
        val index = usuario.indexOfFirst { it.id == usuarioDTO.id }
        if (index != -1) {
            usuario[index] = usuarioDTO
            onSuccess()
        } else {
            onError()
        }
    }

    override fun delete(id: String, onSuccess: () -> Unit, onError: () -> Unit) {
        val removed = usuario.removeIf { it.id == id }
        if (removed) {
            onSuccess()
        } else {
            onError()
        }
    }

    override fun loginUser(
        email: String,
        passwd: String,
        keepLogged: Boolean,
        onSuccess: (UsuarioDTO) -> Unit,
        onError: () -> Unit
    ) {
        val user = usuario.find { it.email == email && it.passwd == passwd }
        if (user != null) {
            currentUser = user
            if (keepLogged) {
                editor.putString("userId", user.id)
                editor.apply()
            }
            onSuccess(user)
        } else {
            onError()
        }
    }

    override fun logoutUser(onSuccess: () -> Unit, onError: () -> Unit) {
        currentUser = null
        editor.remove("userId")
        editor.apply()
        onSuccess()
    }

    override fun getCurrentUser(): UsuarioDTO? {
        val userId = sharedPreferences.getString("userId", null)
        if (userId != null) {
            return usuario.find { it.id == userId }
        }
        return null
    }
}

