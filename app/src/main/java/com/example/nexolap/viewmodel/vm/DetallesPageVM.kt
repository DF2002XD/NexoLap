package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.nexolap.Data.repository.OrdenadorRepo
import com.example.nexolap.Data.repository.OrdenadorSpecsRepo
import com.example.nexolap.viewmodel.uistate.ListaEspecificacionesUIState1
import com.example.nexolap.viewmodel.uistate.ListaOrdenadorSpecsUISrtate1
import com.example.nexolap.viewmodel.uistate.ListaOrdenadoresUIState1
import com.example.nexolap.viewmodel.uistate.OrdenadorSpecsUIState1
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState1
import com.example.nexolap.viewmodel.uistate.especificacionUIState1
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetallesPageVM : ViewModel() {

    private val _ordenadorState = MutableStateFlow(ListaOrdenadoresUIState1())
    val ordenadorState: StateFlow<ListaOrdenadoresUIState1?> = _ordenadorState.asStateFlow()

    private val _especificacionesState = MutableStateFlow(ListaEspecificacionesUIState1())
    val especificacionesState: StateFlow<ListaEspecificacionesUIState1> =
        _especificacionesState.asStateFlow()

    private val _relacionesState = MutableStateFlow(ListaOrdenadorSpecsUISrtate1())
    val relacionesState: StateFlow<ListaOrdenadorSpecsUISrtate1> = _relacionesState.asStateFlow()

    val ordenadorRepo: OrdenadorRepo = OrdenadorRepo()
    val ordenadorSpecsRepo: OrdenadorSpecsRepo = OrdenadorSpecsRepo()

    fun getDetalles(id: Int) {
        if(_ordenadorState.value.listaOrdenadores.any { it.id == id }) return

        ordenadorRepo.read(id, onSucess = { dto ->
            dto?.let {
                _ordenadorState.update { state ->
                    state.copy(
                        listaOrdenadores = listOf(
                            OrdenadorUIState1(it.id, it.nombre, it.imagenPrincipal)
                        )
                    )
                }
            }
        }, onError = { /* Manejar error si es necesario */ })

        ordenadorSpecsRepo.readAll(onSucess = { todasLasRelaciones ->
            val filtradas = todasLasRelaciones.filter { it.id_Ordenador.id == id }

            _relacionesState.update { state ->
                state.copy(
                    listaOrdenadorSpecs = filtradas.map {
                        OrdenadorSpecsUIState1(it.id_Ordenador.id, it.id_Especificacion.id)
                    }
                )
            }

            _especificacionesState.update { state ->
                state.copy(
                    listaEspecificaciones = filtradas.map {
                        especificacionUIState1(
                            it.id_Especificacion.id,
                            it.id_Especificacion.componente,
                            it.id_Especificacion.descripcion
                        )
                    }
                )
            }
        }, onError = { /* Manejar error si es necesario */ })
    }


}


