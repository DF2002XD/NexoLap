package com.example.nexolap.data.retrofit.repo

import com.example.nexolap.data.retrofit.network.NetworkModule
import com.example.nexolap.modelo.OrdenadorSpecsDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementación de la interfaz [IOrdenadorSpecsApiRepo] que gestiona las operaciones de datos
 * para [OrdenadorSpecsDTO] a través de un servicio de red basado en Retrofit.
 */
class OrdenadorSpecsApiRepo private constructor() : IOrdenadorSpecsApiRepo {
    private val apiService = NetworkModule.apiService
    private val gson = Gson()

    companion object {
        @Volatile
        private var INSTANCE: OrdenadorSpecsApiRepo? = null

        fun getInstance(): OrdenadorSpecsApiRepo {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: OrdenadorSpecsApiRepo().also { INSTANCE = it }
            }
        }
    }

    override suspend fun readAll(
        onSuccess: (List<OrdenadorSpecsDTO>) -> Unit,
        onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAll("ordenadorespecs")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val json = gson.toJson(body)
                        val type = object : TypeToken<List<OrdenadorSpecsDTO>>() {}.type
                        val ordenadorSpecs: List<OrdenadorSpecsDTO> = gson.fromJson(json, type)
                        withContext(Dispatchers.Main) {
                            onSuccess(ordenadorSpecs)
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
        onSuccess: (ordenadorSpecsCrado: OrdenadorSpecsDTO?) -> Unit,
        onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getById("ordenadorespecs", id)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val json = gson.toJson(body)
                        val ordenadorSpecs: OrdenadorSpecsDTO = gson.fromJson(json,
                            OrdenadorSpecsDTO::class.java)
                        withContext(Dispatchers.Main) {
                            onSuccess(ordenadorSpecs)
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
        ordenadorSpecs: OrdenadorSpecsDTO,
        onSuccess: (id: String) -> Unit,
        onError: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                // Usamos update (PUT a la carpeta base) para la creación de la relación
                val response = apiService.update("ordenadorespecs", ordenadorSpecs)
                if (response.isSuccessful) {
                    val body = response.body()
                    val json = gson.toJson(body)
                    val jsonObject = gson.fromJson(json, Map::class.java)
                    val serverId = jsonObject["id"]?.toString() ?: ""

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
