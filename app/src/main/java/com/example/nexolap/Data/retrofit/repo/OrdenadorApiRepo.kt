package com.example.nexolap.data.retrofit.repo

import com.example.nexolap.data.retrofit.network.NetworkModule
import com.example.nexolap.modelo.OrdenadorDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementación de [IOrdenadorApiRepo] que maneja las operaciones de datos para objetos
 * [OrdenadorDTO] utilizando un servicio de API basado en Retrofit.
 */
class OrdenadorApiRepo private constructor() : IOrdenadorApiRepo {

    private val apiService = NetworkModule.apiService
    private val gson = Gson()

    companion object {
        @Volatile
        private var INSTANCE: OrdenadorApiRepo? = null

        fun getInstance(): OrdenadorApiRepo {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: OrdenadorApiRepo().also { INSTANCE = it }
            }
        }
    }

    override suspend fun readAll(
        onSuccess: (List<OrdenadorDTO>) -> Unit,
        onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAll("ordenador")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val json = gson.toJson(body)
                        val type = object : TypeToken<List<OrdenadorDTO>>() {}.type
                        val ordenadores: List<OrdenadorDTO> = gson.fromJson(json, type)
                        withContext(Dispatchers.Main) {
                            onSuccess(ordenadores)
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
        onSuccess: (ordenadorCreado: OrdenadorDTO?) -> Unit,
        onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getById("ordenador", id)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val json = gson.toJson(body)
                        val ordenador: OrdenadorDTO = gson.fromJson(
                            json,
                            OrdenadorDTO::class.java
                        )
                        withContext(Dispatchers.Main) {
                            onSuccess(ordenador)
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
        ordenador: OrdenadorDTO,
        onSuccess: (id: String) -> Unit,
        onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                // Usamos update (PUT a la carpeta base) para la creación de un nuevo recurso
                val response = apiService.update("ordenador", ordenador)
                if (response.isSuccessful) {
                    val body = response.body()
                    val json = gson.toJson(body)
                    val jsonObject = gson.fromJson(json, Map::class.java)
                    val serverId = jsonObject["id"]?.toString() ?: ordenador.id

                    withContext(Dispatchers.Main) {
                        onSuccess(serverId)
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
