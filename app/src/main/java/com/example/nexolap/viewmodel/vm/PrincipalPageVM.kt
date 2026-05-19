package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexolap.data.retrofit.network.NetworkModule
import com.example.nexolap.data.retrofit.repo.IOrdenadorApiRepo
import com.example.nexolap.data.retrofit.repo.OrdenadorApiRepo
import com.example.nexolap.viewmodel.uistate.ListaOrdenadoresUIState
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la lógica de negocio y el estado de la interfaz
 * para la pantalla principal (PrincipalPage) de la aplicación.
 */
class PrincipalPageVM : ViewModel() {
    private val _uiState = MutableStateFlow(ListaOrdenadoresUIState())
    val uiState: StateFlow<ListaOrdenadoresUIState> = _uiState.asStateFlow()

    private val repo: IOrdenadorApiRepo = OrdenadorApiRepo.getInstance()

    fun loadData() {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            repo.readAll(
                onSuccess = { list ->
                    _uiState.update {
                        it.copy(
                            listaOrdenadores = list.map { ordenador ->
                                // Si no tiene categorías, le asignamos "Nuevos Lanzamientos" por defecto para que sea visible
                                val categoriasFinales = if (ordenador.categorias.isNullOrEmpty()) {
                                    listOf("Nuevos Lanzamientos")
                                } else {
                                    ordenador.categorias
                                }
                                
                                OrdenadorUIState(
                                    id = ordenador.id,
                                    nombre = ordenador.nombre,
                                    imagenPrincipal = if (ordenador.imagenPrincipal.startsWith("http")) 
                                        ordenador.imagenPrincipal 
                                    else 
                                        NetworkModule.getImageUrl(ordenador.imagenPrincipal),
                                    categorias = categoriasFinales
                                )
                            },
                            isLoading = false
                        )
                    }
                },
                onError = {
                    _uiState.update { it.copy(isLoading = false, error = "Error al cargar los ordenadores") }
                }
            )
        }
    }
}
