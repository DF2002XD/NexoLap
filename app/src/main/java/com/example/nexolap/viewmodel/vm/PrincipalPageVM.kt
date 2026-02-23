package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.nexolap.Data.retrofit.repo.IOrdenadorApiRepo
import com.example.nexolap.Data.retrofit.repo.OrdenadorApiRepo
import com.example.nexolap.viewmodel.uistate.ListaOrdenadoresUIState
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel encargado de gestionar la lógica de negocio y el estado de la interfaz
 * para la pantalla principal (PrincipalPage) de la aplicación.
 *
 * Se encarga de coordinar la obtención de la lista de ordenadores a través del
 * repositorio y transformar los datos obtenidos en un estado consumible por la UI.
 *
 * @property uiState Flujo de estado reactivo que contiene la lista de ordenadores para mostrar en la vista.
 * @property repo Repositorio encargado de realizar las operaciones de red relacionadas con los ordenadores.
 */
class PrincipalPageVM : ViewModel() {
    private val _uiState = MutableStateFlow(ListaOrdenadoresUIState())
    val uiState: StateFlow<ListaOrdenadoresUIState> = _uiState.asStateFlow()

    val repo: IOrdenadorApiRepo = OrdenadorApiRepo()

    fun loadData() {
        repo.readAll(
            { it ->
                _uiState.value = ListaOrdenadoresUIState(it.map {
                    OrdenadorUIState(
                        id = it.id,
                        nombre = it.nombre,
                        imagenPrincipal = it.imagenPrincipal
                    )
                })
            },
            onError = {}
        )
    }
}