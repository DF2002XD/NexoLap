package com.example.nexolap.modelo

/**
 * Objeto de Transferencia de Datos (DTO) que representa la relación entre un ordenador y sus especificaciones técnicas.
 *
 * @property id_Ordenador Información detallada del ordenador asociado a través de su DTO.
 * @property id_Especificacion Información detallada de la especificación técnica asociada a través de su DTO.
 */
data class OrdenadorSpecsDTO(
    var id_Ordenador: OrdenadorDTO, var id_Especificacion: EspecificacionDTO
)

/**
 * Objeto de Transferencia de Datos (DTO) utilizado para las operaciones de red con Retrofit.
 * Representa la relación entre un ordenador y su especificación utilizando sus identificadores únicos.
 *
 * @property id_Ordenador El identificador único del ordenador (ID).
 * @property id_Especificacion El identificador único de la especificación (ID).
 */
data class OrdenadorSpecsDTORetroFit(var id_Ordenador: String, var id_Especificacion: String)
