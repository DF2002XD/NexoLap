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
 * ViewModel encargado de gestionar la lógica de negocio y el estado de la interfaz de usuario
 * para la pantalla de búsqueda de ordenadores.
 */
class BusquedaPageVM : ViewModel() {
    private val _uiState = MutableStateFlow(ListaOrdenadoresUIState())
    val uiState: StateFlow<ListaOrdenadoresUIState> = _uiState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val repo: IOrdenadorApiRepo = OrdenadorApiRepo.getInstance()
    private var allOrdenadores = listOf<OrdenadorUIState>()

    fun obtenerOrdenadores() {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repo.readAll(
                onSuccess = { listaDTO ->
                    allOrdenadores = listaDTO.map {
                        OrdenadorUIState(
                            id = it.id, 
                            nombre = it.nombre, 
                            imagenPrincipal = if (it.imagenPrincipal.startsWith("http")) it.imagenPrincipal else NetworkModule.getImageUrl(it.imagenPrincipal)
                        )
                    }
                    _uiState.update {
                        it.copy(
                            listaOrdenadores = allOrdenadores,
                            isLoading = false
                        )
                    }
                },
                onError = {
                    _uiState.update { it.copy(isLoading = false, error = "Error al realizar la búsqueda") }
                }
            )
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        val filteredList = if (text.isEmpty()) {
            allOrdenadores
        } else {
            allOrdenadores.filter {
                it.nombre.contains(text, ignoreCase = true)
            }
        }
        _uiState.update { it.copy(listaOrdenadores = filteredList) }
    }
}
