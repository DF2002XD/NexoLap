package com.example.nexolap.vista.myComponents


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Un composable personalizado para mostrar una barra de aplicación superior con un título centrado.
 * Utiliza `TopAppBar` de Material3 para proporcionar una apariencia consistente.
 *
 * @param title La cadena de texto que se mostrará como el título centrado de la barra de aplicación.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppTitle(title : String) {
    TopAppBar(
        title = { Text(text = title, color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center) },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppRegreso(){
    TopAppBar(
        title = { Text(text = "", color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center) },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ })
            {
                Icon(Icons.Default.ArrowBack, contentDescription = "Regreso")
            }
        }
    )
}

/**
 * Un composable para una barra de navegación inferior personalizada.
 * Muestra tres iconos (Inicio, Búsqueda y Perfil) distribuidos equitativamente.
 * Cada icono es un botón que ejecuta una acción específica al ser presionado.
 *
 * @param onHomeClick La función lambda que se ejecutará cuando se haga clic en el icono de Inicio.
 * @param onSearchClick La función lambda que se ejecutará cuando se haga clic en el icono de Búsqueda.
 * @param onProfileClick La función lambda que se ejecutará cuando se haga clic en el icono de Perfil.
 */
@Composable
fun ButtomAppBarNav(onHomeClick: () -> Unit, onSearchClick: () -> Unit, onProfileClick: () ->Unit ) {
    BottomAppBar(
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = onHomeClick , modifier = Modifier.weight(1.5f)) {
                Icon(Icons.Default.Home, contentDescription = "Home", modifier = Modifier
                    .height(200.dp)
                    .width(200.dp))
            }
            IconButton(onClick = onSearchClick, modifier = Modifier.weight(1.5f)) {
                Icon(Icons.Default.Search , contentDescription = "Search", modifier = Modifier
                    .height(200.dp)
                    .width(200.dp))
            }
            IconButton(onClick = onProfileClick, modifier = Modifier.weight(1.5f)) {
                Icon(Icons.Default.AccountCircle , contentDescription = "Profile", modifier = Modifier
                    .height(200.dp)
                    .width(200.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewTopAppTitle() {
    TopAppTitle(title = "NexoLap")
}

@Preview
@Composable
fun PreviewButtomAppBarNav() {
    ButtomAppBarNav(onHomeClick = {}, onSearchClick = {}, onProfileClick = {})
}

@Preview
@Composable
fun PreviewTopAppRegreso() {
    TopAppRegreso()
}