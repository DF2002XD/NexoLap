package com.example.nexolap.data.retrofit.repo

import com.example.nexolap.modelo.OrdenadorSpecsDTO

/**
 * Interfaz de repositorio que define las operaciones de acceso a datos para las especificaciones
 * de ordenadores (OrdenadorSpecs) a través de una API de Retrofit.
 */
interface IOrdenadorSpecsApiRepo {
    suspend fun readAll(onSuccess: (List<OrdenadorSpecsDTO>) -> Unit, onError: () -> Unit)

    suspend fun read(
        id: String,
        onSuccess: (ordenadorSpecsCreado: OrdenadorSpecsDTO?) -> Unit,
        onError: () -> Unit
    )

    suspend fun create(
        ordenadorSpecs: OrdenadorSpecsDTO,
        onSuccess: (id: String) -> Unit,
        onError: () -> Unit
    )
}
