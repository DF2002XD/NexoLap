package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexolap.data.retrofit.repo.EspecificacionApiRepo
import com.example.nexolap.data.retrofit.repo.IEspecificacionApiRepo
import com.example.nexolap.data.retrofit.repo.IOrdenadorApiRepo
import com.example.nexolap.data.retrofit.repo.IOrdenadorSpecsApiRepo
import com.example.nexolap.data.retrofit.repo.OrdenadorApiRepo
import com.example.nexolap.data.retrofit.repo.OrdenadorSpecsApiRepo
import com.example.nexolap.modelo.EspecificacionDTO
import com.example.nexolap.modelo.OrdenadorDTO
import com.example.nexolap.modelo.OrdenadorSpecsDTO
import com.example.nexolap.viewmodel.uistate.AddOrdenadorUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

/**
 * ViewModel encargado de la lógica para añadir un nuevo ordenador.
 */
class AddOrdenadorPageVM : ViewModel() {

    private val _uiState = MutableStateFlow(AddOrdenadorUIState())
    val uiState: StateFlow<AddOrdenadorUIState> = _uiState.asStateFlow()

    private val ordenadorRepo: IOrdenadorApiRepo = OrdenadorApiRepo.getInstance()
    private val especificacionRepo: IEspecificacionApiRepo = EspecificacionApiRepo.getInstance()
    private val ordenadorSpecsRepo: IOrdenadorSpecsApiRepo = OrdenadorSpecsApiRepo.getInstance()

    fun onNombreChanged(nombre: String) {
        _uiState.update { it.copy(nombre = nombre) }
    }

    fun onImagenUrlChanged(url: String) {
        _uiState.update { it.copy(imagenUrl = url) }
    }

    fun addSpecField() {
        _uiState.update { it.copy(especificaciones = it.especificaciones + ("" to "")) }
    }

    fun removeSpecField(index: Int) {
        _uiState.update { 
            val newList = it.especificaciones.toMutableList()
            if (newList.size > 1) {
                newList.removeAt(index)
            }
            it.copy(especificaciones = newList)
        }
    }

    fun onSpecChanged(index: Int, componente: String, descripcion: String) {
        _uiState.update { 
            val newList = it.especificaciones.toMutableList()
            newList[index] = componente to descripcion
            it.copy(especificaciones = newList)
        }
    }

    fun onCategoryToggled(category: String) {
        _uiState.update { state ->
            val newCategories = if (state.categorias.contains(category)) {
                state.categorias.filter { it != category }
            } else {
                state.categorias + category
            }
            state.copy(categorias = newCategories)
        }
    }

    fun saveOrdenador() {
        val state = _uiState.value
        if (state.nombre.isBlank() || state.imagenUrl.isBlank()) {
            _uiState.update { it.copy(error = "Nombre e imagen son obligatorios") }
            return
        }

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val ordenadorId = UUID.randomUUID().toString()
            val ordenador = OrdenadorDTO(
                id = ordenadorId,
                nombre = state.nombre,
                imagenPrincipal = state.imagenUrl,
                categorias = state.categorias
            )

            ordenadorRepo.create(ordenador, onSuccess = { serverId ->
                // Procedemos a guardar las especificaciones técnicas usando el ID del servidor
                saveAllSpecifications(serverId, state)
            }, onError = {
                _uiState.update { it.copy(isLoading = false, error = "Error al crear el ordenador") }
            })
        }
    }

    private fun saveAllSpecifications(ordenadorId: String, state: AddOrdenadorUIState) {
        val specsToSave = state.especificaciones.filter { it.first.isNotBlank() && it.second.isNotBlank() }
        
        if (specsToSave.isEmpty()) {
            _uiState.update { it.copy(isLoading = false, success = true) }
            return
        }

        saveNextSpec(ordenadorId, specsToSave, 0)
    }

    private fun saveNextSpec(ordenadorId: String, specs: List<Pair<String, String>>, index: Int) {
        if (index >= specs.size) {
            _uiState.update { it.copy(isLoading = false, success = true) }
            return
        }

        val (comp, desc) = specs[index]
        val specId = UUID.randomUUID().toString()
        val especificacion = EspecificacionDTO(id = specId, componente = comp, descripcion = desc)

        viewModelScope.launch {
            especificacionRepo.create(especificacion, onSuccess = { serverSpecId ->
                val relationship = OrdenadorSpecsDTO(id_Ordenador = ordenadorId, id_Especificacion = serverSpecId)
                viewModelScope.launch {
                    ordenadorSpecsRepo.create(relationship, onSuccess = { _ ->
                        saveNextSpec(ordenadorId, specs, index + 1)
                    }, onError = {
                        saveNextSpec(ordenadorId, specs, index + 1)
                    })
                }
            }, onError = {
                saveNextSpec(ordenadorId, specs, index + 1)
            })
        }
    }


    fun resetState() {
        _uiState.value = AddOrdenadorUIState()
    }
}
