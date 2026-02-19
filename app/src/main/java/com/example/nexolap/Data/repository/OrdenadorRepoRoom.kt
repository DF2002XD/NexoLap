package com.example.nexolap.Data.repository

import android.content.Context
import com.example.nexolap.Data.room.AppDatabase
import com.example.nexolap.Data.room.entity.OrdenadorEntity
import com.example.nexolap.modelo.OrdenadorDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrdenadorRepoRoom(context: Context) : IOrdenadorRepo {
    private val dao = AppDatabase.getDatabase(context).ordenadorDao()
    private val scope = CoroutineScope(Dispatchers.IO)

    private fun OrdenadorEntity.toDTO() = OrdenadorDTO(id, nombre, imagenPrincipal)
    private fun OrdenadorDTO.toEntity() = OrdenadorEntity(id, nombre, imagenPrincipal)

    override fun readAll(onSucess: (List<OrdenadorDTO>) -> Unit, onError: () -> Unit) {
        scope.launch {
            try {
                val lista = dao.getAll().map { it.toDTO() }
                withContext(Dispatchers.Main) { onSucess(lista) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    override fun read(id: Int, onSucess: (especificacionCreado: OrdenadorDTO?) -> Unit, onError: () -> Unit) {
        scope.launch {
            try {
                val item = dao.getById(id)?.toDTO()
                withContext(Dispatchers.Main) { onSucess(item) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }
}
