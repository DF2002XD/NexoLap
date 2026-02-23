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


/**
 * Composable function that represents the details page for a specific computer.
 *
 * It retrieves and displays information about a computer, including its specifications,
 * based on the provided [ordenadorId]. It manages the UI state by observing data
 * from the [DetallesPageVM].
 *
 * @param modifier The [Modifier] to be applied to the layout.
 * @param ordenadorId The unique identifier of the computer to display.
 * @param vm The ViewModel that handles the business logic and provides the state for this page.
 */
@Composable
fun DetallesPage(
    modifier: Modifier = Modifier,
    ordenadorId: String,
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
    DetallesPage(ordenadorId = "")
}
