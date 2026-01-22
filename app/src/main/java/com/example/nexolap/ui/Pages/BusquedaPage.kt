package com.example.nexolap.ui.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.myComponents.Buscador
import com.example.nexolap.myComponents.ButtomAppBarNav
import com.example.nexolap.myComponents.ListVertical


/**
 * Un Composable que representa la página de búsqueda de la aplicación.
 *
 * Esta pantalla está estructurada utilizando un [Scaffold], que proporciona una
 * estructura de diseño estándar. Incluye una barra de navegación inferior personalizada
 * (`ButtomAppBarNav`) para navegar entre las secciones principales de la aplicación
 * (Inicio, Búsqueda, Perfil).
 *
 * El área de contenido principal, dentro de una [Column], contiene un componente de
 * barra de búsqueda (`Buscador`) en la parte superior, seguido de una lista vertical
 * (`ListVertical`) que probablemente muestra los resultados de la búsqueda o una lista
 * de elementos para explorar.
 *
 * @param modifier Un [Modifier] para ser aplicado a este Composable. El valor por defecto es [Modifier].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusquedaPage(modifier: Modifier = Modifier) {
    Scaffold (
        bottomBar = {
            ButtomAppBarNav(
                onHomeClick = { /* Acción al hacer clic en el ícono de inicio */ },
                onSearchClick = { /* Acción al hacer clic en el ícono de búsqueda */ },
                onProfileClick = { /* Acción al hacer clic en el ícono de cuenta */ },
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {
            Buscador()
            ListVertical()
        }
    }
}

@Preview
@Composable
fun PreviewSearchPage() {
    BusquedaPage()
}