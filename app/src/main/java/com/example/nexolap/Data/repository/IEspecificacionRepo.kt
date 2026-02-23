package com.example.nexolap.Data.repository

import com.example.nexolap.modelo.EspecificacionDTO

/**
 * Interfaz de repositorio para gestionar las operaciones de acceso a datos de [EspecificacionDTO].
 * Define los métodos necesarios para consultar información sobre especificaciones técnicas.
 */
interface IEspecificacionRepo {
    fun readAll(onSucess: (List<EspecificacionDTO>) -> Unit, onError: () -> Unit)

    fun read(
        id: Int,
        onSucess: (especificacionCrado: EspecificacionDTO?) -> Unit,
        onError: () -> Unit
    )
}
