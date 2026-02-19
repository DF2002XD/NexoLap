package com.example.nexolap.Data.room.dao

import androidx.room.*
import com.example.nexolap.Data.room.entity.OrdenadorEntity

@Dao
interface OrdenadorDao {
    @Query("SELECT * FROM OrdenadorEntity")
    suspend fun getAll(): List<OrdenadorEntity>

    @Query("SELECT * FROM OrdenadorEntity WHERE id = :id")
    suspend fun getById(id: Int): OrdenadorEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ordenador: OrdenadorEntity)

    @Delete
    suspend fun delete(ordenador: OrdenadorEntity)
}
