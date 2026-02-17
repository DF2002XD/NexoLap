package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexolap.viewmodel.vm.BusquedaPageVM
import com.example.nexolap.vista.myComponents.Buscador
import com.example.nexolap.vista.myComponents.ButtomAppBarNav
import com.example.nexolap.vista.myComponents.ListVertical

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusquedaPage(
    modifier: Modifier = Modifier,
    vm: BusquedaPageVM = viewModel()
) {
    val uiState by vm.uiState.collectAsState()
    val searchText by vm.searchText.collectAsState()

    vm.obtenerOrdenadores()


    Scaffold(
        bottomBar = {
            ButtomAppBarNav(
                onHomeClick = { /* Acción al hacer clic en el ícono de inicio */ },
                onSearchClick = { /* Acción al hacer clic en el ícono de búsqueda */ },
                onProfileClick = { /* Acción al hacer clic en el ícono de cuenta */ },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Buscador(
                searchText = searchText,
                onSearchTextChange = { vm.onSearchTextChange(it) }
            )
            ListVertical(ordenadores = uiState.listaOrdenadores)
        }
    }
}

@Preview
@Composable
fun PreviewSearchPage() {
    BusquedaPage()
}
