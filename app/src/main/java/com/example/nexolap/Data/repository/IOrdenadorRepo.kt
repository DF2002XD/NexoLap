package com.example.nexolap.data.repository

import com.example.nexolap.modelo.OrdenadorDTO

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad [OrdenadorDTO].
 * Proporciona métodos para la recuperación de datos de forma asíncrona mediante callbacks.
 */
interface IOrdenadorRepo {
    fun readAll(onSuccess: (List<OrdenadorDTO>) -> Unit, onError: () -> Unit)

    fun read(id: String, onSuccess: (ordenadorCreado: OrdenadorDTO?) -> Unit, onError: () -> Unit)
}
