package com.example.nexolap.Data.repository

import com.example.nexolap.modelo.EspecificacionDTO

interface IEspecificacionRepo {
    fun readAll(onSucess: (List<EspecificacionDTO>) -> Unit, onError: () -> Unit)

    fun read(
        id: Int,
        onSucess: (especificacionCrado: EspecificacionDTO?) -> Unit,
        onError: () -> Unit
    )

}
