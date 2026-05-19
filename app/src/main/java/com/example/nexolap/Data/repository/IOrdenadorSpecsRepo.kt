package com.example.nexolap.data.repository

import com.example.nexolap.modelo.OrdenadorSpecsDTO

/**
 * Repositorio encargado de gestionar las operaciones de acceso a datos para las especificaciones de ordenadores.
 */
interface IOrdenadorSpecsRepo {
    fun readAll(onSuccess: (List<OrdenadorSpecsDTO>) -> Unit, onError: () -> Unit)

    fun read(
        id: String,
        onSuccess: (ordenadorSpecsCreado: OrdenadorSpecsDTO?) -> Unit,
        onError: () -> Unit
    )
}
