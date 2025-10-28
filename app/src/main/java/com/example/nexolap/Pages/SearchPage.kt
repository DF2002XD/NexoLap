package com.example.nexolap.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.myComponents.ButtomAppBarNav
import com.example.nexolap.myComponents.ListVertical


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(modifier: Modifier = Modifier) {
    Scaffold (
        bottomBar = {
            ButtomAppBarNav(
                onHomeClick = { /* Acción al hacer clic en el ícono de inicio */ },
                onSearchClick = { /* Acción al hacer clic en el ícono de búsqueda */ },
                onProfileClick = { /* Acción al hacer clic en el ícono de cuenta */ },
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SearchBar(
                query = "Search query",
                onQueryChange = { /* Manejar el cambio de consulta */ },
                onSearch = { /* Manejar la acción de búsqueda */ },
                active = false,
                onActiveChange = { /* Manejar el cambio de estado activo */ }
            ){

            }
            ListVertical()
        }

    }

}

@Preview
@Composable
fun PreviewSearchPage() {
    SearchPage()
}