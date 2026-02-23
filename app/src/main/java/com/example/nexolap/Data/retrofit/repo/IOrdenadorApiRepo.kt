package com.example.nexolap.Data.retrofit.repo

import com.example.nexolap.modelo.OrdenadorDTORetroFit

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad [OrdenadorDTORetroFit]
 * a través de una API remota utilizando Retrofit.
 *
 * Proporciona métodos para la obtención masiva e individual de ordenadores mediante
 * funciones de retrollamada (callbacks).
 */
interface IOrdenadorApiRepo {
    fun readAll(onSucess: (List<OrdenadorDTORetroFit>) -> Unit, onError: () ->Unit)

    fun read(id: String, onSucess: (especificacionCreado : OrdenadorDTORetroFit?) -> Unit, onError: () ->Unit)
}