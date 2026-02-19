package com.example.nexolap.Data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EspecificacionEntity(
    @PrimaryKey val id: Int,
    val componente: String,
    val descripcion: String
)
