package com.example.nexolap.vista.Pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.Data.ListaData
import com.example.nexolap.vista.myComponents.ListHorizontal
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.nexolap.viewmodel.vm.PrincipalPageVM
import androidx.lifecycle.viewmodel.compose.viewModel


/**
 * Composable function that represents the main page of the application.
 * It displays several horizontal lists of computers categorized by sections
 * such as "Más Vendidos", "Populares", and "Nuevos Lanzamientos".
 *
 * @param modifier The [Modifier] to be applied to the layout.
 * @param onOrdenadorClick Callback function triggered when a computer item is clicked,
 * receiving the unique identifier of the selected computer.
 * @param vm The [PrincipalPageVM] ViewModel that manages the state and data logic for this page.
 */
@Composable
fun PrincipalPage(
    modifier: Modifier = Modifier,
    onOrdenadorClick: (String) -> Unit = {},
    vm: PrincipalPageVM = viewModel()
) {
    val uiState by vm.uiState.collectAsState()
    vm.loadData()

    LazyColumn(modifier = modifier) {
        item {
            ListHorizontal(
                listaData = ListaData("Más Vendidos"),
                ordenadores = uiState.listaOrdenadores,
                onOrdenadorClick = { ordenadorId ->
                    onOrdenadorClick(ordenadorId)
                }
            )
        }
        item {
            ListHorizontal(
                listaData = ListaData("Populares"),
                ordenadores = uiState.listaOrdenadores,
                onOrdenadorClick = { ordenadorId ->
                    onOrdenadorClick(ordenadorId)
                }
            )
        }
        item {
            ListHorizontal(
                listaData = ListaData("Nuevos Lanzamientos"),
                ordenadores = uiState.listaOrdenadores,
                onOrdenadorClick = { ordenadorId ->
                    onOrdenadorClick(ordenadorId)
                }
            )
        }
    }
}


@Preview
@Composable
fun PreviewHomePage() {
    PrincipalPage()
}
