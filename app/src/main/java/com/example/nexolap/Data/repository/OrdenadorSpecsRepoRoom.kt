package com.example.nexolap.Data.repository

import android.content.Context
import com.example.nexolap.Data.room.AppDatabase
import com.example.nexolap.modelo.EspecificacionDTO
import com.example.nexolap.modelo.OrdenadorDTO
import com.example.nexolap.modelo.OrdenadorSpecsDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrdenadorSpecsRepoRoom(context: Context) : IOrdenadorSpecsRepo {
    private val dao = AppDatabase.getDatabase(context).ordenadorSpecsDao()
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun readAll(onSucess: (List<OrdenadorSpecsDTO>) -> Unit, onError: () -> Unit) {
        scope.launch {
            try {
                val lista = dao.getAllWithDetails().map {
                    OrdenadorSpecsDTO(
                        id_Ordenador = OrdenadorDTO(it.ordenador.id, it.ordenador.nombre, it.ordenador.imagenPrincipal),
                        id_Especificacion = EspecificacionDTO(it.especificacion.id, it.especificacion.componente, it.especificacion.descripcion)
                    )
                }
                withContext(Dispatchers.Main) { onSucess(lista) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    override fun read(id: Int, onSucess: (ordenadorSpecsCrado: OrdenadorSpecsDTO?) -> Unit, onError: () -> Unit) {
        // En este caso, 'id' podría no ser suficiente si la PK es compuesta, 
        // pero seguimos la interfaz read(id). Aquí asumo que buscas por id de Ordenador por ejemplo.
        scope.launch {
            try {
                // Implementación simplificada para cumplir con la interfaz
                val all = dao.getAllWithDetails()
                val item = all.find { it.ordenador.id == id }?.let {
                    OrdenadorSpecsDTO(
                        id_Ordenador = OrdenadorDTO(it.ordenador.id, it.ordenador.nombre, it.ordenador.imagenPrincipal),
                        id_Especificacion = EspecificacionDTO(it.especificacion.id, it.especificacion.componente, it.especificacion.descripcion)
                    )
                }
                withContext(Dispatchers.Main) { onSucess(item) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }
}
