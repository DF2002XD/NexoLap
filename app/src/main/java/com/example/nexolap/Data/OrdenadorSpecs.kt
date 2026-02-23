package com.example.nexolap.Data

/**
 * Representa la asociación entre un ordenador y sus especificaciones técnicas detalladas.
 * Esta clase actúa como un vínculo de datos entre una entidad [Ordenador] y una [Especificacion].
 *
 * @property id_Ordenador La instancia de [Ordenador] a la que pertenecen las especificaciones.
 * @property id_Especificacion La instancia de [Especificacion] que contiene los detalles técnicos.
 */
data class OrdenadorSpecs(
    val id_Ordenador: Ordenador,
    val id_Especificacion: Especificacion,
)

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
    val id_Especificacion: especificacionRetrofit,
)


