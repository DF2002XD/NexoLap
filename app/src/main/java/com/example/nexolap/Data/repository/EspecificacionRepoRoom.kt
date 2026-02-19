package com.example.nexolap.Data.repository

import android.content.Context
import com.example.nexolap.Data.room.AppDatabase
import com.example.nexolap.Data.room.entity.EspecificacionEntity
import com.example.nexolap.modelo.EspecificacionDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EspecificacionRepoRoom(context: Context) : IEspecificacionRepo {
    private val dao = AppDatabase.getDatabase(context).especificacionDao()
    private val scope = CoroutineScope(Dispatchers.IO)

    private fun EspecificacionEntity.toDTO() = EspecificacionDTO(id, componente, descripcion)
    private fun EspecificacionDTO.toEntity() = EspecificacionEntity(id, componente, descripcion)

    override fun readAll(onSucess: (List<EspecificacionDTO>) -> Unit, onError: () -> Unit) {
        scope.launch {
            try {
                val lista = dao.getAll().map { it.toDTO() }
                withContext(Dispatchers.Main) { onSucess(lista) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    override fun read(id: Int, onSucess: (especificacionCrado: EspecificacionDTO?) -> Unit, onError: () -> Unit) {
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
