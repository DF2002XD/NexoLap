package com.example.nexolap.Data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nexolap.Data.room.dao.EspecificacionDao
import com.example.nexolap.Data.room.dao.OrdenadorDao
import com.example.nexolap.Data.room.dao.OrdenadorSpecsDao
import com.example.nexolap.Data.room.dao.UserPreferencesDao
import com.example.nexolap.Data.room.entity.EspecificacionEntity
import com.example.nexolap.Data.room.entity.OrdenadorEntity
import com.example.nexolap.Data.room.entity.OrdenadorSpecsEntity
import com.example.nexolap.Data.room.entity.User

@Database(
    entities = [
        User::class,
        EspecificacionEntity::class,
        OrdenadorEntity::class,
        OrdenadorSpecsEntity::class
    ],
    version = 2 // Incrementado de 1 a 2 por las nuevas tablas
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userPreferencesDao(): UserPreferencesDao
    abstract fun especificacionDao(): EspecificacionDao
    abstract fun ordenadorDao(): OrdenadorDao
    abstract fun ordenadorSpecsDao(): OrdenadorSpecsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .fallbackToDestructiveMigration() // Útil durante desarrollo si cambias el esquema
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
