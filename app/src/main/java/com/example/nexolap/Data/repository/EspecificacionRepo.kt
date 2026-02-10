package com.example.nexolap.Data.repository

import com.example.nexolap.modelo.EspecificacionDTO


class EspecificacionRepo : IEspecificacionRepo{

    companion object{
        var specs = ArrayList(
            listOf(
                //Apple MacBook Air 13 (ID Ordenador: 0)
                EspecificacionDTO(
                    0,
                    "Procesador",
                    "Chip M2 de Apple con CPU de 8 núcleos y GPU de 8 núcleos"
                ),
                EspecificacionDTO(
                    1,
                    "Memoria RAM",
                    "8 GB de memoria unificada (ampliable a 16 GB o 24 GB)"
                ),
                EspecificacionDTO(2, "Almacenamiento", "256 GB SSD (ampliable a 512 GB, 1 TB o 2 TB)"),
                EspecificacionDTO(3, "Pantalla", "13,6 pulgadas Liquid Retina con True Tone, 500 nits"),
                EspecificacionDTO(4, "Batería", "Hasta 18 horas de reproducción de vídeo"),
                EspecificacionDTO(
                    5,
                    "Puertos",
                    "Puerto de carga MagSafe 3, dos puertos Thunderbolt/USB 4"
                ),
                EspecificacionDTO(6, "Peso", "1,24 kg"),

                //Apple MacBook Pro 14 (ID Ordenador: 1)
                EspecificacionDTO(
                    7,
                    "Procesador",
                    "Chip M3 Pro de Apple con CPU de hasta 12 núcleos y GPU de hasta 18 núcleos"
                ),
                EspecificacionDTO(8, "Memoria RAM", "18 GB de memoria unificada (ampliable a 36 GB)"),
                EspecificacionDTO(9, "Almacenamiento", "512 GB SSD (ampliable a 1 TB, 2 TB o 4 TB)"),
                EspecificacionDTO(
                    10,
                    "Pantalla",
                    "14,2 pulgadas Liquid Retina XDR, ProMotion hasta 120 Hz"
                ),
                EspecificacionDTO(11, "Batería", "Hasta 18 horas de reproducción de vídeo"),
                EspecificacionDTO(
                    12,
                    "Puertos",
                    "Tres puertos Thunderbolt 4, puerto HDMI, ranura para tarjetas SDXC, MagSafe 3"
                ),
                EspecificacionDTO(13, "Peso", "1,61 kg"),

                //Apple MacBook Pro 16 (ID Ordenador: 2)
                EspecificacionDTO(
                    14,
                    "Procesador",
                    "Chip M3 Max de Apple con CPU de 16 núcleos y GPU de 40 núcleos"
                ),
                EspecificacionDTO(
                    15,
                    "Memoria RAM",
                    "48 GB de memoria unificada (ampliable a 64 GB o 128 GB)"
                ),
                EspecificacionDTO(16, "Almacenamiento", "1 TB SSD (ampliable a 2 TB, 4 TB u 8 TB)"),
                EspecificacionDTO(
                    17,
                    "Pantalla",
                    "16,2 pulgadas Liquid Retina XDR, ProMotion hasta 120 Hz"
                ),
                EspecificacionDTO(18, "Batería", "Hasta 22 horas de reproducción de vídeo"),
                EspecificacionDTO(
                    19,
                    "Puertos",
                    "Tres puertos Thunderbolt 4, puerto HDMI, ranura para tarjetas SDXC, MagSafe 3"
                ),
                EspecificacionDTO(20, "Peso", "2,16 kg")
            )
        )
    }


    override fun readAll(onSucess: (List<EspecificacionDTO>) -> Unit, onError: () -> Unit) {
        onSucess(specs)
    }

    override fun read(
        id: Int,
        onSucess: (especificacionCrado: EspecificacionDTO?) -> Unit,
        onError: () -> Unit
    ) {
        onSucess(specs.find { it.id == id })

    }
}