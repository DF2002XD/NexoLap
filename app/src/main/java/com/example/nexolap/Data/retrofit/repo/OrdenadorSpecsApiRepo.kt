package com.example.nexolap.Data.retrofit.repo

import com.example.nexolap.Data.retrofit.network.NetworkModule
import com.example.nexolap.modelo.OrdenadorSpecsDTORetroFit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Implementación de la interfaz [IOrdenadorSpecsApiRepo] que gestiona las operaciones de datos
 * para [OrdenadorSpecsDTORetroFit] a través de un servicio de red basado en Retrofit.
 *
 * Esta clase se encarga de realizar llamadas asíncronas a la API utilizando Corrutinas de Kotlin,
 * alternando entre [Dispatchers.IO] para las peticiones de red y [Dispatchers.Main] para
 * ejecutar los callbacks de éxito o error en el hilo principal.
 *
 * @property apiService Servicio de Retrofit utilizado para realizar las peticiones HTTP definido en [NetworkModule].
 * @property gson Instancia de [Gson] utilizada para serializar y deserializar dinámicamente los cuerpos de las respuestas.
 */
class OrdenadorSpecsApiRepo : IOrdenadorSpecsApiRepo {
    private val apiService = NetworkModule.apiService
    private val gson = Gson()

    override fun readAll(
        onSucess: (List<OrdenadorSpecsDTORetroFit>) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Asumiendo que el endpoint para ordenadorSpecs es "ordenadorespecs"
                val response = apiService.getAll("ordenadorespecs")
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val json = gson.toJson(body)
                            val type = object : TypeToken<List<OrdenadorSpecsDTORetroFit>>() {}.type
                            val ordenadorSpecs: List<OrdenadorSpecsDTORetroFit> = gson.fromJson(json, type)
                            onSucess(ordenadorSpecs)
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
        onSucess: (ordenadorSpecsCrado: OrdenadorSpecsDTORetroFit?) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getById("ordenadorespecs", id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val json = gson.toJson(body)
                            val ordenadorSpecs: OrdenadorSpecsDTORetroFit = gson.fromJson(json,
                                OrdenadorSpecsDTORetroFit::class.java)
                            onSucess(ordenadorSpecs)
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
