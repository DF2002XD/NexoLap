package com.example.nexolap.data

/**
 * Represents the association between a computer ([Ordenador]) and its technical specifications ([Especificacion]).
 *
 * This data class serves as a junction or mapping entity to link specific hardware details
 * to a particular computer instance.
 *
 * @property id_Ordenador The computer entity associated with these specifications.
 * @property id_Especificacion The specific technical detail or requirement linked to the computer.
 */
data class OrdenadorSpecsApi(
    val id_Ordenador: OrdenadorRetrofit,
    val id_Especificacion: EspecificacionRetrofit,
)


