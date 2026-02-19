package com.example.nexolap.Data.room.dao

import androidx.room.*
import com.example.nexolap.Data.room.entity.EspecificacionEntity
import com.example.nexolap.Data.room.entity.OrdenadorEntity
import com.example.nexolap.Data.room.entity.OrdenadorSpecsEntity

data class OrdenadorWithSpecs(
    @Embedded val specs: OrdenadorSpecsEntity,
    @Relation(
        parentColumn = "idOrdenador",
        entityColumn = "id"
    )
    val ordenador: OrdenadorEntity,
    @Relation(
        parentColumn = "idEspecificacion",
        entityColumn = "id"
    )
    val especificacion: EspecificacionEntity
)

@Dao
interface OrdenadorSpecsDao {
    @Transaction
    @Query("SELECT * FROM OrdenadorSpecsEntity")
    suspend fun getAllWithDetails(): List<OrdenadorWithSpecs>

    @Transaction
    @Query("SELECT * FROM OrdenadorSpecsEntity WHERE idOrdenador = :idOrdenador AND idEspecificacion = :idEspecificacion")
    suspend fun getByIds(idOrdenador: Int, idEspecificacion: Int): OrdenadorWithSpecs?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(specs: OrdenadorSpecsEntity)

    @Delete
    suspend fun delete(specs: OrdenadorSpecsEntity)
}
