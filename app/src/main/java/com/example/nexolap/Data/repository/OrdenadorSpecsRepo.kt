package com.example.nexolap.data.repository

import com.example.nexolap.modelo.OrdenadorSpecsDTO

/**
 * Repositorio encargado de gestionar la relación entre ordenadores y sus especificaciones técnicas.
 */
class OrdenadorSpecsRepo : IOrdenadorSpecsRepo {

    var ordenadorSpecs = ArrayList(
        listOf(
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0].id, EspecificacionRepo.specs[0].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0].id, EspecificacionRepo.specs[1].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0].id, EspecificacionRepo.specs[2].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0].id, EspecificacionRepo.specs[3].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0].id, EspecificacionRepo.specs[4].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0].id, EspecificacionRepo.specs[5].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[0].id, EspecificacionRepo.specs[6].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1].id, EspecificacionRepo.specs[7].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1].id, EspecificacionRepo.specs[8].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1].id, EspecificacionRepo.specs[9].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1].id, EspecificacionRepo.specs[10].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1].id, EspecificacionRepo.specs[11].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1].id, EspecificacionRepo.specs[12].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[1].id, EspecificacionRepo.specs[13].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2].id, EspecificacionRepo.specs[14].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2].id, EspecificacionRepo.specs[15].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2].id, EspecificacionRepo.specs[16].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2].id, EspecificacionRepo.specs[17].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2].id, EspecificacionRepo.specs[18].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2].id, EspecificacionRepo.specs[19].id),
            OrdenadorSpecsDTO(OrdenadorRepo.ordenador[2].id, EspecificacionRepo.specs[20].id),
        )
    )

    override fun readAll(
        onSuccess: (List<OrdenadorSpecsDTO>) -> Unit, onError: () -> Unit
    ) {
        onSuccess(ordenadorSpecs)
    }

    override fun read(
        id: String, onSuccess: (ordenadorSpecsCrado: OrdenadorSpecsDTO?) -> Unit, onError: () -> Unit
    ) {
        onSuccess(ordenadorSpecs.find { it.id_Ordenador == id })
    }
}

