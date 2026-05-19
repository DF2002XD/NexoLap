package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexolap.data.retrofit.network.NetworkModule
import com.example.nexolap.data.retrofit.repo.EspecificacionApiRepo
import com.example.nexolap.data.retrofit.repo.IEspecificacionApiRepo
import com.example.nexolap.data.retrofit.repo.IOrdenadorApiRepo
import com.example.nexolap.data.retrofit.repo.IOrdenadorSpecsApiRepo
import com.example.nexolap.data.retrofit.repo.OrdenadorApiRepo
import com.example.nexolap.data.retrofit.repo.OrdenadorSpecsApiRepo
import com.example.nexolap.viewmodel.uistate.EspecificacionUIState
import com.example.nexolap.viewmodel.uistate.ListaEspecificacionesUIState
import com.example.nexolap.viewmodel.uistate.ListaOrdenadorSpecsUIState
import com.example.nexolap.viewmodel.uistate.ListaOrdenadoresUIState
import com.example.nexolap.viewmodel.uistate.OrdenadorSpecsUIState
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar y proporcionar los datos detallados de un ordenador específico.
 */
class DetallesPageVM : ViewModel() {

    private val _ordenadorState = MutableStateFlow(ListaOrdenadoresUIState())
    val ordenadorState: StateFlow<ListaOrdenadoresUIState> = _ordenadorState.asStateFlow()

    private val _especificacionesState = MutableStateFlow(ListaEspecificacionesUIState())
    val especificacionesState: StateFlow<ListaEspecificacionesUIState> = _especificacionesState.asStateFlow()

    private val _relacionesState = MutableStateFlow(ListaOrdenadorSpecsUIState())
    val relacionesState: StateFlow<ListaOrdenadorSpecsUIState> = _relacionesState.asStateFlow()

    private val ordenadorRepo: IOrdenadorApiRepo = OrdenadorApiRepo.getInstance()
    private val ordenadorSpecsRepo: IOrdenadorSpecsApiRepo = OrdenadorSpecsApiRepo.getInstance()
    private val especificacionesRepo: IEspecificacionApiRepo = EspecificacionApiRepo.getInstance()

    fun getDetalles(id: String) {
        // Limpiamos estados previos para evitar mostrar datos de otro ordenador
        _ordenadorState.value = ListaOrdenadoresUIState(isLoading = true)
        _relacionesState.value = ListaOrdenadorSpecsUIState(isLoading = true)
        _especificacionesState.value = ListaEspecificacionesUIState(isLoading = true)

        viewModelScope.launch {
            ordenadorRepo.read(id, onSuccess = { dto ->
                if (dto != null) {
                    _ordenadorState.update { it.copy(
                        listaOrdenadores = listOf(OrdenadorUIState(
                            id = dto.id, 
                            nombre = dto.nombre, 
                            imagenPrincipal = if (dto.imagenPrincipal.startsWith("http")) dto.imagenPrincipal else NetworkModule.getImageUrl(dto.imagenPrincipal)
                        )),
                        isLoading = false
                    )}
                } else {
                    _ordenadorState.update { it.copy(isLoading = false, error = "Ordenador no encontrado") }
                }
            }, onError = {
                _ordenadorState.update { it.copy(isLoading = false, error = "Error al conectar con el servidor") }
            })
        }

        viewModelScope.launch {
            ordenadorSpecsRepo.readAll(onSuccess = { todasLasRelaciones ->
                val filtradas = todasLasRelaciones.filter { it.id_Ordenador == id }
                
                _relacionesState.update { it.copy(
                    listaOrdenadorSpecs = filtradas.map { rel -> OrdenadorSpecsUIState(rel.id_Ordenador, rel.id_Especificacion) },
                    isLoading = false
                )}

                if (filtradas.isNotEmpty()) {
                    cargarEspecificaciones(filtradas.map { it.id_Especificacion })
                } else {
                    _especificacionesState.update { it.copy(isLoading = false) }
                }
            }, onError = {
                _relacionesState.update { it.copy(isLoading = false, error = "Error al cargar relaciones") }
                _especificacionesState.update { it.copy(isLoading = false) }
            })
        }
    }

    private fun cargarEspecificaciones(ids: List<String>) {
        viewModelScope.launch {
            especificacionesRepo.readAll(onSuccess = { todas ->
                val filtradas = todas.filter { spec -> ids.contains(spec.id) }
                _especificacionesState.update { it.copy(
                    listaEspecificaciones = filtradas.map { spec -> EspecificacionUIState(spec.id, spec.componente, spec.descripcion) },
                    isLoading = false
                )}
            }, onError = {
                _especificacionesState.update { it.copy(isLoading = false, error = "Error al cargar especificaciones") }
            })
        }
    }
}
