package com.example.nexolap.Data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrdenadorEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val imagenPrincipal: Int
)
