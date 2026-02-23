package com.example.nexolap.Data.retrofit.repo

import com.example.nexolap.Data.retrofit.network.NetworkModule
import com.example.nexolap.modelo.OrdenadorDTORetroFit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Implementación de [IOrdenadorApiRepo] que maneja las operaciones de datos para objetos
 * [OrdenadorDTORetroFit] utilizando un servicio de API basado en Retrofit.
 *
 * Este repositorio gestiona las solicitudes de red asíncronas mediante Coroutines para obtener
 * registros de ordenadores, ya sea de forma individual o múltiple, desde un servidor remoto.
 *
 * @property apiService El servicio de Retrofit utilizado para realizar las peticiones de red.
 * @property gson La instancia de Gson utilizada para la serialización y deserialización de JSON.
 */
class OrdenadorApiRepo : IOrdenadorApiRepo {

    private val apiService = NetworkModule.apiService
    private val gson = Gson()

    override fun readAll(
        onSucess: (List<OrdenadorDTORetroFit>) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getAll("ordenador")
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val json = gson.toJson(body)
                            val type = object : TypeToken<List<OrdenadorDTORetroFit>>() {}.type
                            val ordenadores: List<OrdenadorDTORetroFit> = gson.fromJson(json, type)
                            onSucess(ordenadores)
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
        onSucess: (ordenadorCreado: OrdenadorDTORetroFit?) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getById("ordenador", id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val json = gson.toJson(body)
                            val ordenador: OrdenadorDTORetroFit = gson.fromJson(
                                json,
                                OrdenadorDTORetroFit::class.java
                            )
                            onSucess(ordenador)
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
}
