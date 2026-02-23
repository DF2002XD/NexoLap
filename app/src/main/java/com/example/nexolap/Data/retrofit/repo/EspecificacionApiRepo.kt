package com.example.nexolap.Data.retrofit.repo

import com.example.nexolap.Data.retrofit.network.NetworkModule
import com.example.nexolap.modelo.EspecificacionDTORetroFit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Implementación del repositorio para la gestión de datos de especificaciones a través de una API remota.
 *
 * Esta clase implementa la interfaz [IEspecificacionApiRepo] utilizando Retrofit para las peticiones
 * de red y Gson para la serialización y deserialización de objetos. Gestiona las operaciones
 * asíncronas mediante Corrutinas de Kotlin, ejecutando las llamadas en el despachador de E/S (IO)
 * y devolviendo los resultados en el hilo principal (Main).
 *
 * @property apiService Servicio de red para realizar las llamadas a la API, proporcionado por [NetworkModule].
 * @property gson Instancia de Gson utilizada para convertir las respuestas de la API en objetos DTO.
 */
class EspecificacionApiRepo : IEspecificacionApiRepo {

    private val apiService = NetworkModule.apiService
    private val gson = Gson()

    override fun readAll(
        onSucess: (List<EspecificacionDTORetroFit>) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getAll("especificaciones")
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val json = gson.toJson(body)
                            val type = object : TypeToken<List<EspecificacionDTORetroFit>>() {}.type
                            val especificaciones: List<EspecificacionDTORetroFit> =
                                gson.fromJson(json, type)
                            onSucess(especificaciones)
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
        onSucess: (especificacionCrado: EspecificacionDTORetroFit?) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getById("especificaciones", id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val json = gson.toJson(body)
                            val especificacion: EspecificacionDTORetroFit = gson.fromJson(
                                json,
                                EspecificacionDTORetroFit::class.java
                            )
                            onSucess(especificacion)
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
