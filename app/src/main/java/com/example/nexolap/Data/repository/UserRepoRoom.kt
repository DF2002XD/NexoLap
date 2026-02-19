package com.example.nexolap.Data.repository

import android.content.Context
import com.example.nexolap.Data.room.AppDatabase
import com.example.nexolap.Data.room.entity.User
import com.example.nexolap.modelo.UsuarioDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.runBlocking

class UserRepoRoom(context: Context) : IUsuarioRepo {
    private val dao = AppDatabase.getDatabase(context).userPreferencesDao()
    private val scope = CoroutineScope(Dispatchers.IO)

    // Funciones de extensión para convertir entre Entity y DTO
    private fun User.toDTO() = UsuarioDTO(id, name, email, password, keepLogged, color)
    private fun UsuarioDTO.toEntity() = User(id, nombre, correo, contraseña, keepLogged, color)

    override fun readAll(onSucess: (List<UsuarioDTO>) -> Unit, onError: () -> Unit) {
        scope.launch {
            try {
                val users = dao.getAll().map { it.toDTO() }
                withContext(Dispatchers.Main) { onSucess(users) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    override fun read(id: Int, onSucess: (usuarioCreado: UsuarioDTO?) -> Unit, onError: () -> Unit) {
        scope.launch {
            try {
                val user = dao.getUserById(id)?.toDTO()
                withContext(Dispatchers.Main) { onSucess(user) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    override fun update(usuarioDTO: UsuarioDTO, onSucess: () -> Unit, onError: () -> Unit) {
        scope.launch {
            try {
                dao.update(usuarioDTO.toEntity())
                withContext(Dispatchers.Main) { onSucess() }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    override fun delete(id: Int, onSucess: () -> Unit, onError: () -> Unit) {
        scope.launch {
            try {
                dao.deleteById(id)
                withContext(Dispatchers.Main) { onSucess() }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    override fun loginUser(
        correo: String,
        contrasenha: String,
        keepLogged: Boolean,
        onSucess: (UsuarioDTO) -> Unit,
        onError: () -> Unit
    ) {
        scope.launch {
            try {
                // Buscamos al usuario por correo y contraseña en la base de datos local
                val user = dao.getAll().find { it.email == correo && it.password == contrasenha }
                if (user != null) {
                    // Si lo encontramos y quiere mantener sesión, actualizamos su estado
                    val updatedUser = user.copy(keepLogged = keepLogged)
                    dao.update(updatedUser)
                    withContext(Dispatchers.Main) { onSucess(updatedUser.toDTO()) }
                } else {
                    withContext(Dispatchers.Main) { onError() }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    override fun loggoutUSer(onSucess: () -> Unit, onError: () -> Unit) {
        scope.launch {
            try {
                val user = dao.getKeepLoggedUser()
                if (user != null) {
                    dao.update(user.copy(keepLogged = false))
                }
                withContext(Dispatchers.Main) { onSucess() }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    override fun getCurrentUser(): UsuarioDTO? {
        // Al ser una llamada síncrona en la interfaz, usamos runBlocking para obtener el resultado
        // Nota: En una app real, lo ideal sería que esta función también fuera asíncrona.
        return runBlocking(Dispatchers.IO) {
            dao.getKeepLoggedUser()?.toDTO()
        }
    }
}
