package com.example.nexolap.data.retrofit.repo

import com.example.nexolap.modelo.EspecificacionDTO

/**
 * Interfaz de repositorio que define las operaciones de acceso a datos para la entidad [EspecificacionDTO]
 * a través de servicios API REST.
 */
interface IEspecificacionApiRepo {
    suspend fun readAll(onSuccess: (List<EspecificacionDTO>) -> Unit, onError: () -> Unit)

    suspend fun read(
        id: String,
        onSuccess: (especificacionCreado: EspecificacionDTO?) -> Unit,
        onError: () -> Unit
    )

    suspend fun create(
        especificacion: EspecificacionDTO,
        onSuccess: (id: String) -> Unit,
        onError: () -> Unit
    )
}
