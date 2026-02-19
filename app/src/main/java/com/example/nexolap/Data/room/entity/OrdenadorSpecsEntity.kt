package com.example.nexolap.Data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["idOrdenador", "idEspecificacion"],
    foreignKeys = [
        ForeignKey(
            entity = OrdenadorEntity::class,
            parentColumns = ["id"],
            childColumns = ["idOrdenador"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = EspecificacionEntity::class,
            parentColumns = ["id"],
            childColumns = ["idEspecificacion"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OrdenadorSpecsEntity(
    val idOrdenador: Int,
    val idEspecificacion: Int
)
