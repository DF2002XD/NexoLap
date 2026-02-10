package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.nexolap.Data.repository.OrdenadorRepo
import com.example.nexolap.viewmodel.uistate.ListaOrdenadoresUIState1
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState1
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PrincipalPageVM : ViewModel() {
    private val _uiState = MutableStateFlow(ListaOrdenadoresUIState1())
    val uiState: StateFlow<ListaOrdenadoresUIState1> = _uiState.asStateFlow()

    val repo: OrdenadorRepo = OrdenadorRepo()

    fun cargarDatos(){
        _uiState.value = ListaOrdenadoresUIState1()
    }


    fun loadData(){
        repo.readAll(
            {
                _uiState.value = ListaOrdenadoresUIState1(it.map {
                    OrdenadorUIState1(
                        it.id,
                        it.nombre,
                        it.imagenPrincipal
                    )
                })
            },
            onError ={

            }
        )
    }
}