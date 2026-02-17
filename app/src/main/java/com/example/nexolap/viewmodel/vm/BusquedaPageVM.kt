package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.nexolap.Data.repository.OrdenadorRepo
import com.example.nexolap.viewmodel.uistate.ListaOrdenadoresUIState1
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState1
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BusquedaPageVM : ViewModel() {
    private val _uiState = MutableStateFlow(ListaOrdenadoresUIState1())
    val uiState: StateFlow<ListaOrdenadoresUIState1> = _uiState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val repo: OrdenadorRepo = OrdenadorRepo()
    private var allOrdenadores = listOf<OrdenadorUIState1>()


    fun obtenerOrdenadores() {
        repo.readAll(
            onSucess = { listaDTO ->
                allOrdenadores = listaDTO.map {
                    OrdenadorUIState1(it.id, it.nombre, it.imagenPrincipal)
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
