package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.nexolap.viewmodel.vm.BusquedaPageVM
import com.example.nexolap.vista.myComponents.Buscador
import com.example.nexolap.vista.myComponents.ListVertical

/**
 * Composable function that represents the search page of the application.
 * It allows users to search for computers and displays a filtered list of results.
 *
 * @param modifier The [Modifier] to be applied to the layout.
 * @param onOrdenadorClick Callback function triggered when a computer item is selected,
 * receiving the computer's ID or unique identifier as a string.
 * @param vm The [BusquedaPageVM] ViewModel that manages the state and logic for this page.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusquedaPage(
    modifier: Modifier = Modifier,
    onOrdenadorClick: (String) -> Unit = {},
    vm: BusquedaPageVM = viewModel()
) {
    val uiState by vm.uiState.collectAsState()
    val searchText by vm.searchText.collectAsState()

    LaunchedEffect(Unit) {
        vm.obtenerOrdenadores()
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Buscador(
                searchText = searchText,
                onSearchTextChange = { vm.onSearchTextChange(it) }
            )
            
            if (uiState.isLoading && uiState.listaOrdenadores.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 32.dp))
            }

            ListVertical(
                ordenadores = uiState.listaOrdenadores,
                onOrdenadorClick = onOrdenadorClick
            )
        }

        uiState.error?.let { errorMsg ->
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
fun PreviewSearchPage() {
    BusquedaPage()
}
