package com.example.nexolap.Data.repository

import com.example.nexolap.modelo.OrdenadorSpecsDTO

interface IOrdenadorSpecsRepo {
    fun readAll(onSucess: (List<OrdenadorSpecsDTO>) -> Unit, onError: () ->Unit)

    fun read(id: Int, onSucess: (ordenadorSpecsCrado : OrdenadorSpecsDTO?) -> Unit, onError: () ->Unit)
}