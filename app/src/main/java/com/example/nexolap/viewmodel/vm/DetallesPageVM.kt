package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.nexolap.Data.retrofit.repo.IOrdenadorApiRepo
import com.example.nexolap.Data.retrofit.repo.IOrdenadorSpecsApiRepo
import com.example.nexolap.Data.retrofit.repo.OrdenadorApiRepo
import com.example.nexolap.Data.retrofit.repo.OrdenadorSpecsApiRepo
import com.example.nexolap.viewmodel.uistate.ListaEspecificacionesUIState
import com.example.nexolap.viewmodel.uistate.ListaOrdenadorSpecsUIState
import com.example.nexolap.viewmodel.uistate.ListaOrdenadoresUIState
import com.example.nexolap.viewmodel.uistate.OrdenadorSpecsUIState
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel encargado de gestionar y proporcionar los datos detallados de un ordenador específico.
 *
 * Esta clase se encarga de coordinar la obtención de información general del ordenador
 * y sus especificaciones técnicas asociadas a través de los repositorios correspondientes,
 * exponiendo el estado de la interfaz de usuario mediante [StateFlow].
 *
 * @property ordenadorState Flujo de estado que contiene la información básica del ordenador seleccionado.
 * @property especificacionesState Flujo de estado que contiene la lista de especificaciones disponibles.
 * @property relacionesState Flujo de estado que contiene las relaciones entre ordenadores y especificaciones.
 * @property ordenadorRepo Repositorio para acceder a los datos generales de los ordenadores.
 * @property ordenadorSpecsRepo Repositorio para acceder a las relaciones de especificaciones técnicas.
 */
class DetallesPageVM : ViewModel() {

    private val _ordenadorState = MutableStateFlow(ListaOrdenadoresUIState())
    val ordenadorState: StateFlow<ListaOrdenadoresUIState?> = _ordenadorState.asStateFlow()

    private val _especificacionesState = MutableStateFlow(ListaEspecificacionesUIState())
    val especificacionesState: StateFlow<ListaEspecificacionesUIState> = _especificacionesState.asStateFlow()

    private val _relacionesState = MutableStateFlow(ListaOrdenadorSpecsUIState())
    val relacionesState: StateFlow<ListaOrdenadorSpecsUIState> = _relacionesState.asStateFlow()

    val ordenadorRepo: IOrdenadorApiRepo = OrdenadorApiRepo()
    val ordenadorSpecsRepo: IOrdenadorSpecsApiRepo = OrdenadorSpecsApiRepo()

    fun getDetalles(id: String) {
        if (_ordenadorState.value.listaOrdenadores.any { it.id == id }) return

        ordenadorRepo.read(id, onSucess = { dto ->
            dto?.let {
                _ordenadorState.update { state ->
                    state.copy(
                        listaOrdenadores = listOf(
                            OrdenadorUIState(it.id, it.nombre, it.imagenPrincipal)
                        )
                    )
                }
            }
        }, onError = { /* Manejar error si es necesario */ })

        ordenadorSpecsRepo.readAll(onSucess = { todasLasRelaciones ->
            val filtradas = todasLasRelaciones.filter { it.id_Ordenador == id }

            _relacionesState.update { state ->
                state.copy(
                    listaOrdenadorSpecs = filtradas.map {
                        OrdenadorSpecsUIState(
                            it.id_Ordenador,
                            it.id_Especificacion
                        )
                    }
                )
            }
        }, onError = { /* Manejar error si es necesario */ })
    }
}


