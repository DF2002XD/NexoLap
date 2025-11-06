package com.example.nexolap.Pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.myComponents.ButtomAppBarNav
import com.example.nexolap.myComponents.ListData
import com.example.nexolap.myComponents.ListHorizontal
import com.example.nexolap.myComponents.TopAppTitle

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppTitle(
                title = "NexoLap"
            )
        },
        bottomBar = {
            ButtomAppBarNav(
                onHomeClick = { /* Acción al hacer clic en el ícono de inicio */ },
                onSearchClick = { /* Acción al hacer clic en el ícono de búsqueda */ },
                onProfileClick = { /* Acción al hacer clic en el ícono de cuenta */ },
            )
        }

    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                ListHorizontal(listData = ListData("Más Vendidos"))
            }
            item {
                ListHorizontal(listData = ListData("Populares"))
            }
            item {
                ListHorizontal(listData = ListData("Nuevos Lanzamientos"))
            }
        }
    }
}


@Preview
@Composable
fun PreviewHomePage() {
    HomePage()
}