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


@Composable
fun PrincipalPage(
    modifier: Modifier = Modifier,
    onOrdenadorClick: (Int) -> Unit = {},
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
