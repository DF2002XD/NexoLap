package com.example.nexolap.Data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.nexolap.Data.room.entity.User

@Dao
interface UserPreferencesDao {

    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE id = :id LIMIT 1")
    fun getUserById(id: Int): User?

    @Query("SELECT * FROM User WHERE keepLogged = 1 LIMIT 1")
    fun getKeepLoggedUser(): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("DELETE FROM User WHERE id = :id")
    fun deleteById(id: Int)

    @Query("DELETE FROM User")
    fun deleteAll()
}
