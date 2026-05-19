package com.example.nexolap.data.retrofit.repo

import android.content.Context
import com.example.nexolap.data.retrofit.network.NetworkModule
import com.example.nexolap.modelo.UsuarioDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementación del repositorio de usuarios que gestiona la persistencia de datos mediante
 * una API remota (Retrofit) y una sesión local (SharedPreferences).
 */
class UsuarioApiRepo(context: Context) : IUsuarioApiRepo {

    private val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    private val editor = sharedPreferences.edit()
    private val apiService = NetworkModule.apiService
    private val gson = Gson()


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
        private var INSTANCE: UsuarioApiRepo? = null

        fun init(context: Context) {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = UsuarioApiRepo(context.applicationContext)
                    }
                }
            }
        }

        fun getInstance(): UsuarioApiRepo {
            return INSTANCE
                ?: throw IllegalStateException("UsuarioRepo no inicializado. Llama a UsuarioRepo.init(context) en tu Activity.")
        }
    }

    override suspend fun readAll(
        onSuccess: (List<UsuarioDTO>) -> Unit,
        onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAll("user")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val json = gson.toJson(body)
                        val type = object : TypeToken<List<UsuarioDTO>>() {}.type
                        val usuarios: List<UsuarioDTO> = gson.fromJson(json, type)
                        withContext(Dispatchers.Main) {
                            onSuccess(usuarios)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onError()
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError()
                }
            }
        }
    }

    override suspend fun read(
        id: String,
        onSuccess: (usuarioCreado: UsuarioDTO?) -> Unit,
        onError: () -> Unit
    ) {
        val local = usuario.find { it.id == id }
        if (local != null) {
            onSuccess(local)
            return
        }

        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getById("user", id)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val json = gson.toJson(body)
                        val usuarioObtenido: UsuarioDTO = gson.fromJson(
                            json,
                            UsuarioDTO::class.java
                        )
                        withContext(Dispatchers.Main) {
                            onSuccess(usuarioObtenido)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onSuccess(null)
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError()
                }
            }
        }
    }

    override suspend fun create(
        usuarioDTO: UsuarioDTO,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                val responseRegister = apiService.register(usuarioDTO)

                if (responseRegister.isSuccessful) {
                    val usuarioDesdeApi = responseRegister.body()

                    if (usuarioDesdeApi != null) {
                        val serverId = usuarioDesdeApi.id
                        val usuarioParaCarpeta = usuarioDesdeApi.copy(
                            passwd = usuarioDTO.passwd,
                            color = usuarioDTO.color
                        )

                        apiService.insert("user", serverId, usuarioParaCarpeta)

                        withContext(Dispatchers.Main) {
                            usuario.add(usuarioParaCarpeta)
                            currentUser = usuarioParaCarpeta
                            onSuccess()
                        }
                    } else {
                        withContext(Dispatchers.Main) { onError() }
                    }
                } else {
                    withContext(Dispatchers.Main) { onError() }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError()
                }
            }
        }
    }

    override suspend fun update(
        usuarioDTO: UsuarioDTO,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val index = usuario.indexOfFirst { it.id == usuarioDTO.id }
        if (index != -1) {
            usuario[index] = usuarioDTO
        } else {
            usuario.add(usuarioDTO)
        }

        if (currentUser?.id == usuarioDTO.id) {
            currentUser = usuarioDTO
        }

        withContext(Dispatchers.IO) {
            try {
                apiService.insert("user", usuarioDTO.id, usuarioDTO)
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError()
                }
            }
        }
    }

    override suspend fun delete(id: String, onSuccess: () -> Unit, onError: () -> Unit) {
        usuario.removeAll { it.id == id }
        if (currentUser?.id == id) {
            currentUser = null
        }

        withContext(Dispatchers.IO) {
            try {
                apiService.delete("user", id)
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError()
                }
            }
        }
    }

    override suspend fun loginUser(
        correo: String,
        contrasenha: String,
        keepLogged: Boolean,
        onSuccess: (UsuarioDTO) -> Unit, onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                val responseAll = apiService.getAll("user")
                var userEncontrado: UsuarioDTO? = null

                if (responseAll.isSuccessful) {
                    val body = responseAll.body()
                    if (body != null) {
                        val json = gson.toJson(body)
                        val type = object : TypeToken<List<UsuarioDTO>>() {}.type
                        val list: List<UsuarioDTO> = gson.fromJson(json, type)
                        userEncontrado =
                            list.find { it.email.equals(correo.trim(), ignoreCase = true) }
                    }
                }

                if (userEncontrado == null) {
                    withContext(Dispatchers.Main) { onError() }
                    return@withContext
                }

                val loginReq = UsuarioDTO(
                    id = "",
                    name = userEncontrado.name,
                    email = correo.trim(),
                    passwd = contrasenha.trim()
                )

                val response = apiService.login(loginReq)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val usuarioApiOriginal = body
                        val usuarioApi = usuarioApiOriginal.copy(
                            color = userEncontrado.color,
                            keepLogged = keepLogged,
                            passwd = contrasenha.trim() // Preservamos la contraseña para futuras sesiones
                        )

                        currentUser = usuarioApi

                        val idx = usuario.indexOfFirst { it.id == usuarioApi.id }
                        if (idx != -1) usuario[idx] = usuarioApi else usuario.add(usuarioApi)

                        if (keepLogged) {
                            editor.putString("userId", usuarioApi.id)
                        } else {
                            editor.remove("userId")
                        }
                        editor.apply()

                        // ACTUALIZACIÓN EN SERVIDOR
                        try {
                            apiService.insert("user", usuarioApi.id, usuarioApi)
                        } catch (e: Exception) {
                            // Error log
                        }

                        withContext(Dispatchers.Main) {
                            onSuccess(usuarioApi)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onError()
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    override fun logoutUser(onSuccess: () -> Unit, onError: () -> Unit) {
        currentUser = null
        editor.remove("userId")
        editor.apply()
        onSuccess()
    }

    override fun getCurrentUser(): UsuarioDTO? {
        return currentUser
    }

    override suspend fun checkStoredSession(onSuccess: (UsuarioDTO) -> Unit, onError: () -> Unit) {
        val storedId = sharedPreferences.getString("userId", null)
        if (storedId == null) {
            onError()
            return
        }

        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getById("user", storedId)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val json = gson.toJson(body)
                        val user: UsuarioDTO =
                            gson.fromJson(json, UsuarioDTO::class.java)
                        currentUser = user
                        withContext(Dispatchers.Main) {
                            onSuccess(user)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onError()
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError()
                }
            }
        }
    }
}
