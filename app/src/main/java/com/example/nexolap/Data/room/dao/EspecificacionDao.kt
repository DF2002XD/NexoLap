package com.example.nexolap.Data.room.dao

import androidx.room.*
import com.example.nexolap.Data.room.entity.EspecificacionEntity

@Dao
interface EspecificacionDao {
    @Query("SELECT * FROM EspecificacionEntity")
    suspend fun getAll(): List<EspecificacionEntity>

    @Query("SELECT * FROM EspecificacionEntity WHERE id = :id")
    suspend fun getById(id: Int): EspecificacionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(especificacion: EspecificacionEntity)

    @Delete
    suspend fun delete(especificacion: EspecificacionEntity)
}
