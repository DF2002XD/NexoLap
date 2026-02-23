package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.nexolap.Data.retrofit.repo.IOrdenadorApiRepo
import com.example.nexolap.Data.retrofit.repo.OrdenadorApiRepo
import com.example.nexolap.viewmodel.uistate.ListaOrdenadoresUIState
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel encargado de gestionar la lógica de negocio y el estado de la interfaz de usuario
 * para la pantalla de búsqueda de ordenadores.
 *
 * Proporciona flujos de datos para la lista de ordenadores filtrados y el texto de búsqueda actual,
 * interactuando con el repositorio [IOrdenadorApiRepo] para obtener la información necesaria.
 *
 * @property uiState Flujo de estado que contiene la lista de ordenadores a mostrar.
 * @property searchText Flujo de estado que contiene el texto actual ingresado por el usuario en la barra de búsqueda.
 */
class BusquedaPageVM : ViewModel() {
    private val _uiState = MutableStateFlow(ListaOrdenadoresUIState())
    val uiState: StateFlow<ListaOrdenadoresUIState> = _uiState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val repo: IOrdenadorApiRepo = OrdenadorApiRepo()
    private var allOrdenadores = listOf<OrdenadorUIState>()

    fun obtenerOrdenadores() {
        repo.readAll(
            onSucess = { listaDTO ->
                allOrdenadores = listaDTO.map {
                    OrdenadorUIState(it.id, it.nombre, it.imagenPrincipal)
                }
                _uiState.update { it.copy(listaOrdenadores = allOrdenadores) }
            },
            onError = {
                // Manejar error si es necesario
            }
        )
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
