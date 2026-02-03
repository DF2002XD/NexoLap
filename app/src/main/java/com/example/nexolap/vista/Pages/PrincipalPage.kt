package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.Data.ListaData
import com.example.nexolap.vista.myComponents.ButtomAppBarNav
import com.example.nexolap.vista.myComponents.ListHorizontal
import com.example.nexolap.vista.myComponents.TopAppTitle


/**
 * Función Composable que representa la pantalla principal o "página principal" de la aplicación.
 *
 * Esta pantalla está estructurada con un diseño de [Scaffold], proporcionando una estructura
 * consistente con una barra de aplicación superior, una barra de navegación inferior y el área
 * de contenido principal. El área de contenido muestra varias listas horizontales de elementos,
 * como "Más Vendidos", "Populares" y "Nuevos Lanzamientos", utilizando un [LazyColumn].
 *
 * @param modifier Un [Modifier] que se aplicará al diseño raíz de la página.
 */
@Composable
fun PrincipalPage(modifier: Modifier = Modifier) {
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
                ListHorizontal(listaData = ListaData("Más Vendidos"))
            }
            item {
                ListHorizontal(listaData = ListaData("Populares"))
            }
            item {
                ListHorizontal(listaData = ListaData("Nuevos Lanzamientos"))
            }
        }
    }
}


@Preview
@Composable
fun PreviewHomePage() {
    PrincipalPage()
}