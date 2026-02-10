package com.example.nexolap.Data.repository

import com.example.nexolap.modelo.OrdenadorSpecsDTO

class OrdenadorSpecsRepo: IOrdenadorSpecsRepo {

    var ordenadorSpecs = ArrayList(
        listOf(
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0], EspecificacionRepo.specs[0]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0], EspecificacionRepo.specs[1]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0], EspecificacionRepo.specs[2]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0], EspecificacionRepo.specs[3]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0], EspecificacionRepo.specs[4]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0], EspecificacionRepo.specs[5]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0], EspecificacionRepo.specs[6]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1], EspecificacionRepo.specs[7]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1], EspecificacionRepo.specs[8]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1], EspecificacionRepo.specs[9]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1], EspecificacionRepo.specs[10]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1], EspecificacionRepo.specs[11]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1], EspecificacionRepo.specs[12]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1], EspecificacionRepo.specs[13]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2], EspecificacionRepo.specs[14]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2], EspecificacionRepo.specs[15]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2], EspecificacionRepo.specs[16]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2], EspecificacionRepo.specs[17]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2], EspecificacionRepo.specs[18]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2], EspecificacionRepo.specs[19]),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2], EspecificacionRepo.specs[20]),
        )
    )

    override fun readAll(
        onSucess: (List<OrdenadorSpecsDTO>) -> Unit,
        onError: () -> Unit
    ) {
        onSucess(ordenadorSpecs)
    }

    override fun read(
        id: Int,
        onSucess: (ordenadorSpecsCrado: OrdenadorSpecsDTO?) -> Unit,
        onError: () -> Unit
    ) {
        onSucess(ordenadorSpecs.find { it.id_Ordenador.id == id })

    }

}