package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexolap.viewmodel.vm.DetallesPageVM
import com.example.nexolap.vista.myComponents.Detalles


@Composable
fun DetallesPage(
    modifier: Modifier = Modifier,
    ordenadorId: Int,
    vm: DetallesPageVM = viewModel()
) {

    val ordenadorState by vm.ordenadorState.collectAsState()
    val especificacionesState by vm.especificacionesState.collectAsState()
    val relacionesState by vm.relacionesState.collectAsState()


    vm.getDetalles(ordenadorId)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val ordenador = ordenadorState?.listaOrdenadores?.firstOrNull()
        if (ordenador != null && relacionesState.listaOrdenadorSpecs.isNotEmpty()) {
            Detalles(
                ordenador = ordenador,
                especificaciones = especificacionesState.listaEspecificaciones
            )
        } else if (ordenador != null) {
            Text(text = "Este ordenador no tiene especificaciones registradas.")
        } else {
            Text(text = "Este ordenador no existe.")
        }
    }
}

@Preview
@Composable
fun DetallesPagePreview() {
    DetallesPage(ordenadorId = 1)
}
