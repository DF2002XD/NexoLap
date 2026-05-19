package com.example.nexolap.data.retrofit.repo

import com.example.nexolap.modelo.OrdenadorDTO

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad [OrdenadorDTO]
 * a través de una API remota utilizando Retrofit.
 */
interface IOrdenadorApiRepo {
    suspend fun readAll(onSuccess: (List<OrdenadorDTO>) -> Unit, onError: () -> Unit)

    suspend fun read(id: String, onSuccess: (ordenadorCreado: OrdenadorDTO?) -> Unit, onError: () -> Unit)

    suspend fun create(ordenador: OrdenadorDTO, onSuccess: (id: String) -> Unit, onError: () -> Unit)

}
