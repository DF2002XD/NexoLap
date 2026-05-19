package com.example.nexolap.data.repository

import com.example.nexolap.modelo.EspecificacionDTO

/**
 * Interfaz de repositorio para gestionar las operaciones de acceso a datos de [EspecificacionDTO].
 * Define los métodos necesarios para consultar información sobre especificaciones técnicas.
 */
interface IEspecificacionRepo {
    fun readAll(onSuccess: (List<EspecificacionDTO>) -> Unit, onError: () -> Unit)

    fun read(
        id: String,
        onSuccess: (especificacionCreado: EspecificacionDTO?) -> Unit,
        onError: () -> Unit
    )
}

