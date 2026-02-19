package com.example.nexolap.Data.repository

import android.content.Context
import com.example.nexolap.modelo.UsuarioDTO

class UsuarioRepo(context: Context) : IUsuarioRepo {

    private val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    companion object {
        var usuario = ArrayList(
            listOf(
                UsuarioDTO(
                    id = 0,
                    nombre = "Admin",
                    correo = "admin@gmail.com",
                    contraseña = "admin"
                ),
                UsuarioDTO(
                    id = 1,
                    nombre = "Usuario",
                    correo = "usuario@gmail.com",
                    contraseña = "usuario"
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

    override fun update(usuarioDTO: UsuarioDTO, onSucess: () -> Unit, onError: () -> Unit) {
        val index = usuario.indexOfFirst { it.id == usuarioDTO.id }
        if (index != -1) {
            usuario[index] = usuarioDTO
            onSucess()
        } else {
            onError()
        }
    }

    override fun delete(id: Int, onSucess: () -> Unit, onError: () -> Unit) {
        val removed = usuario.removeIf { it.id == id }
        if (removed) {
            onSucess()
        } else {
            onError()
        }
    }

    override fun loginUser(
        correo: String,
        contrasenha: String,
        keepLogged: Boolean,
        onSucess: (UsuarioDTO) -> Unit,
        onError: () -> Unit
    ) {
        val user = usuario.find { it.correo == correo && it.contraseña == contrasenha }
        if (user != null) {
            currentUser = user
            if (keepLogged) {
                editor.putInt("userId", user.id)
                editor.apply()
            }
            onSucess(user)
        } else {
            onError()
        }
    }

    override fun loggoutUSer(onSucess: () -> Unit, onError: () -> Unit) {
        currentUser = null
        editor.remove("userId")
        editor.apply()
        onSucess()
    }

    override fun getCurrentUser(): UsuarioDTO? {
        val userId = sharedPreferences.getInt("userId", -1)
        if (userId != -1) {
            return usuario.find { it.id == userId }
        }
        return null
    }

}