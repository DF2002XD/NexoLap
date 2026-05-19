package com.example.nexolap.data.retrofit.repo

import com.example.nexolap.data.retrofit.network.NetworkModule
import com.example.nexolap.modelo.EspecificacionDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementación del repositorio para la gestión de datos de especificaciones a través de una API remota.
 */
class EspecificacionApiRepo private constructor() : IEspecificacionApiRepo {

    private val apiService = NetworkModule.apiService
    private val gson = Gson()

    companion object {
        @Volatile
        private var INSTANCE: EspecificacionApiRepo? = null

        fun getInstance(): EspecificacionApiRepo {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: EspecificacionApiRepo().also { INSTANCE = it }
            }
        }
    }

    override suspend fun readAll(
        onSuccess: (List<EspecificacionDTO>) -> Unit,
        onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAll("especificacion")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val json = gson.toJson(body)
                        val type = object : TypeToken<List<EspecificacionDTO>>() {}.type
                        val especificaciones: List<EspecificacionDTO> =
                            gson.fromJson(json, type)
                        withContext(Dispatchers.Main) {
                            onSuccess(especificaciones)
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
        onSuccess: (especificacionCrado: EspecificacionDTO?) -> Unit,
        onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getById("especificacion", id)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val json = gson.toJson(body)
                        val especificacion: EspecificacionDTO = gson.fromJson(
                            json,
                            EspecificacionDTO::class.java
                        )
                        withContext(Dispatchers.Main) {
                            onSuccess(especificacion)
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
        especificacion: EspecificacionDTO,
        onSuccess: (id: String) -> Unit,
        onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                // Usamos update (PUT a la carpeta base) para la creación de un nuevo recurso
                val response = apiService.update("especificacion", especificacion)
                if (response.isSuccessful) {
                    val body = response.body()
                    val json = gson.toJson(body)
                    val jsonObject = gson.fromJson(json, Map::class.java)
                    val serverId = jsonObject["id"]?.toString() ?: especificacion.id

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
