package com.example.nexolap.Data.repository

import com.example.nexolap.modelo.OrdenadorDTO

interface IOrdenadorRepo {
    fun readAll(onSucess: (List<OrdenadorDTO>) -> Unit, onError: () ->Unit)

    fun read(id: Int, onSucess: (especificacionCrado : OrdenadorDTO?) -> Unit, onError: () ->Unit)
}