package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    LaunchedEffect(ordenadorId) {
        vm.getDetalles(ordenadorId)
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (ordenadorState.isLoading || relacionesState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val ordenador = ordenadorState.listaOrdenadores.firstOrNull()
            if (ordenador != null) {
                Detalles(
                    ordenador = ordenador,
                    especificaciones = especificacionesState.listaEspecificaciones
                )
                
                if (relacionesState.listaOrdenadorSpecs.isEmpty() && !relacionesState.isLoading) {
                    Text(
                        text = "Este ordenador no tiene especificaciones registradas.",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else if (!ordenadorState.isLoading) {
                Text(
                    text = ordenadorState.error ?: "Este ordenador no existe.",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        val generalError = ordenadorState.error ?: relacionesState.error
        generalError?.let { errorMsg ->
            Text(
                text = errorMsg,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun DetallesPagePreview() {
    DetallesPage(ordenadorId = "")
}
