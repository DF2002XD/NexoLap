package com.example.nexolap.vista.myComponents


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexolap.R

/**
 * Un composable personalizado para mostrar una barra de aplicación superior con un título centrado.
 * Utiliza `TopAppBar` de Material3 para proporcionar una apariencia consistente.
 *
 * @param title La cadena de texto que se mostrará como el título centrado de la barra de aplicación.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppTitle(title : String, onBackClick: (() -> Unit)? = null,
                onLogoutClick: (() -> Unit)? = null,
                onProfileDetailsClick: (() -> Unit)? = null) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (onBackClick == null) {
                Text(
                    text = title, color = Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        },
        navigationIcon = {
            if(onBackClick != null){
                IconButton(onClick = { onBackClick}){
                    Icon(Icons.Default.ArrowBack, contentDescription = "Regreso")
                }
            }
        },
        actions = {
            if (onLogoutClick != null || onProfileDetailsClick != null) {
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "Perfil",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        if (onProfileDetailsClick != null) {
                            DropdownMenuItem(
                                text = { Text("Detalles del Perfil") },
                                onClick = {
                                    expanded = false
                                    onProfileDetailsClick()
                                }
                            )
                        }
                        if (onLogoutClick != null) {
                            DropdownMenuItem(
                                text = { Text("Cerrar Sesión") },
                                onClick = {
                                    expanded = false
                                    onLogoutClick()
                                }
                            )
                        }
                    }
                }
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
    TopAppTitle(title = "NexoLap",onLogoutClick = {}, onProfileDetailsClick = {})
}

@Preview
@Composable
fun PreviewTopAppTitleWithBack() {
    TopAppTitle(title = "NexoLap", onBackClick = {})

}

@Preview
@Composable
fun PreviewButtomAppBarNav() {
    ButtomAppBarNav(onHomeClick = {}, onSearchClick = {}, onProfileClick = {})
}