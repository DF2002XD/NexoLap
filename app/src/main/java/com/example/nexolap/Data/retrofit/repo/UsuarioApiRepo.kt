package com.example.nexolap.Data.retrofit.repo

import android.content.Context
import com.example.nexolap.Data.retrofit.network.NetworkModule
import com.example.nexolap.modelo.UsuarioDTORetroFit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Implementación del repositorio de usuarios que gestiona la persistencia de datos mediante
 * una API remota (Retrofit) y una sesión local (SharedPreferences).
 *
 * Esta clase centraliza las operaciones CRUD, el proceso de autenticación (login/logout)
 * y la verificación de sesiones persistentes. Utiliza corrutinas de Kotlin para realizar
 * las operaciones de red en hilos de fondo y devuelve los resultados en el hilo principal.
 *
 * Sigue el patrón Singleton para garantizar una única instancia de acceso a los datos
 * en toda la aplicación.
 *
 * @property context El contexto de la aplicación utilizado para inicializar SharedPreferences.
 */
class UsuarioApiRepo(context: Context) : IUsuarioApiRepo {

    private val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    private val editor = sharedPreferences.edit()
    private val apiService = NetworkModule.apiService
    private val gson = Gson()


    companion object {
        var usuario = ArrayList(
            listOf(
                UsuarioDTORetroFit(
                    id = "0",
                    name = "Admin",
                    email = "admin@gmail.com",
                    passwd = "admin"
                ),
                UsuarioDTORetroFit(
                    id = "1",
                    name = "Usuario",
                    email = "usuario@gmail.com",
                    passwd = "usuario"
                )
            )
        )
        var currentUser: UsuarioDTORetroFit? = null

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

    override fun readAll(
        onSucess: (List<UsuarioDTORetroFit>) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getAll("user")
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val json = gson.toJson(body)
                            val type = object : TypeToken<List<UsuarioDTORetroFit>>() {}.type
                            val usuarios: List<UsuarioDTORetroFit> = gson.fromJson(json, type)
                            onSucess(usuarios)
                        } else {
                            onError()
                        }
                    } else {
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

    override fun read(
        id: String,
        onSucess: (usuarioCreado: UsuarioDTORetroFit?) -> Unit,
        onError: () -> Unit
    ) {
        val local = usuario.find { it.id == id }
        if (local != null) {
            onSucess(local)
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getById("user", id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val json = gson.toJson(body)
                            val usuarioObtenido: UsuarioDTORetroFit = gson.fromJson(
                                json,
                                UsuarioDTORetroFit::class.java
                            )
                            onSucess(usuarioObtenido)
                        } else {
                            onSucess(null)
                        }
                    } else {
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

    override fun create(
        usuarioDTO: UsuarioDTORetroFit,
        onSucess: () -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val responseRegister = apiService.register(usuarioDTO)

                if (responseRegister.isSuccessful) {
                    val usuarioDesdeApi = responseRegister.body()

                    if (usuarioDesdeApi != null) {
                        val serverId = usuarioDesdeApi.id
                        val usuarioParaCarpeta = usuarioDesdeApi.copy(passwd = usuarioDTO.passwd)

                        apiService.insert("user", serverId, usuarioParaCarpeta)

                        withContext(Dispatchers.Main) {
                            usuario.add(usuarioDesdeApi)
                            currentUser = usuarioDesdeApi
                            onSucess()
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

    override fun update(
        usuarioDTO: UsuarioDTORetroFit,
        onSucess: () -> Unit,
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

        CoroutineScope(Dispatchers.IO).launch {
            try {
                apiService.insert("user", usuarioDTO.id, usuarioDTO)
                withContext(Dispatchers.Main) {
                    onSucess()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onSucess()
                }
            }
        }
    }

    override fun delete(id: String, onSucess: () -> Unit, onError: () -> Unit) {
        usuario.removeAll { it.id == id }
        if (currentUser?.id == id) {
            currentUser = null
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                apiService.delete("user", id)
                withContext(Dispatchers.Main) {
                    onSucess()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onSucess()
                }
            }
        }
    }

    override fun loginUser(
        correo: String,
        contrasenha: String,
        keepLogged: Boolean,
        onSucess: (UsuarioDTORetroFit) -> Unit, onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val responseAll = apiService.getAll("user")
                var userEncontrado: UsuarioDTORetroFit? = null

                if (responseAll.isSuccessful) {
                    val body = responseAll.body()
                    if (body != null) {
                        val json = gson.toJson(body)
                        val type = object : TypeToken<List<UsuarioDTORetroFit>>() {}.type
                        val list: List<UsuarioDTORetroFit> = gson.fromJson(json, type)
                        userEncontrado =
                            list.find { it.email.equals(correo.trim(), ignoreCase = true) }
                    }
                }

                if (userEncontrado == null) {
                    withContext(Dispatchers.Main) { onError() }
                    return@launch
                }

                val loginReq = UsuarioDTORetroFit(
                    id = "",
                    name = userEncontrado.name,
                    email = correo.trim(),
                    passwd = contrasenha.trim()
                )

                val response = apiService.login(loginReq)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val usuarioApi = response.body()
                        if (usuarioApi != null) {
                            usuarioApi.color = userEncontrado.color
                            usuarioApi.keepLogged = keepLogged

                            currentUser = usuarioApi

                            val idx = usuario.indexOfFirst { it.id == usuarioApi.id }
                            if (idx != -1) usuario[idx] = usuarioApi else usuario.add(usuarioApi)

                            if (keepLogged) {
                                editor.putString("userId", usuarioApi.id)
                            } else {
                                editor.remove("userId")
                            }
                            editor.apply()

                            // ACTUALIZACIÓN EN SERVIDOR: Guardamos el objeto completo con keepLogged y color
                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    apiService.insert("user", usuarioApi.id, usuarioApi)
                                } catch (e: Exception) {
                                    android.util.Log.e("REPO", "Error actualizando JSON tras login")
                                }
                            }

                            onSucess(usuarioApi)
                        } else {
                            onError()
                        }
                    } else {
                        onError()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    override fun loggoutUSer(onSucess: () -> Unit, onError: () -> Unit) {
        currentUser = null
        editor.remove("userId")
        editor.apply()
        onSucess()
    }

    override fun getCurrentUser(): UsuarioDTORetroFit? {
        return currentUser
    }

    override fun checkStoredSession(onSucess: (UsuarioDTORetroFit) -> Unit, onError: () -> Unit) {
        val storedId = sharedPreferences.getString("userId", null)
        if (storedId == null) {
            onError()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getById("user", storedId)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val json = gson.toJson(body)
                            val user: UsuarioDTORetroFit =
                                gson.fromJson(json, UsuarioDTORetroFit::class.java)
                            currentUser = user
                            onSucess(user)
                        } else {
                            onError()
                        }
                    } else {
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
