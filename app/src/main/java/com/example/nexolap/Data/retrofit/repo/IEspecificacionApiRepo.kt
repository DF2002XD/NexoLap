package com.example.nexolap.Data.retrofit.repo

import com.example.nexolap.modelo.EspecificacionDTORetroFit

/**
 * Interfaz de repositorio que define las operaciones de acceso a datos para la entidad [EspecificacionDTORetroFit]
 * a través de servicios API REST.
 *
 * Proporciona métodos para la recuperación de especificaciones de forma asíncrona mediante callbacks.
 */
interface IEspecificacionApiRepo {
    fun readAll(onSucess: (List<EspecificacionDTORetroFit>) -> Unit, onError: () -> Unit)

    fun read(
        id: String,
        onSucess: (especificacionCrado: EspecificacionDTORetroFit?) -> Unit,
        onError: () -> Unit
    )
}
