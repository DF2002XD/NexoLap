package com.example.nexolap.Data.retrofit.repo

import com.example.nexolap.modelo.OrdenadorSpecsDTORetroFit

/**
 * Interfaz de repositorio que define las operaciones de acceso a datos para las especificaciones
 * de ordenadores (OrdenadorSpecs) a través de una API de Retrofit.
 *
 * Proporciona métodos para la recuperación remota de información técnica de equipos.
 */
interface IOrdenadorSpecsApiRepo {
    fun readAll(onSucess: (List<OrdenadorSpecsDTORetroFit>) -> Unit, onError: () -> Unit)

    fun read(
        id: String,
        onSucess: (ordenadorSpecsCrado: OrdenadorSpecsDTORetroFit?) -> Unit,
        onError: () -> Unit
    )
}