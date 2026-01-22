package com.example.nexolap.ui.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.myComponents.ButtomAppBarNav
import com.example.nexolap.myComponents.Perfil

/**
 * Un Composable que representa la pantalla del perfil de usuario.
 *
 * Esta pantalla utiliza un [Scaffold] para proporcionar una estructura de diseño básica.
 * Contiene una [Column] que centra su contenido horizontalmente, y dentro de esa
 * columna, muestra el composable [Perfil], que representa el contenido principal del perfil.
 *
 * @param modifier El modificador que se aplicará al diseño.
 */
@Composable
fun PerfilPage(modifier: Modifier = Modifier){
    Scaffold(
        bottomBar = {
            ButtomAppBarNav(
                onHomeClick = { /* Acción al hacer clic en el ícono de inicio */ },
                onSearchClick = { /* Acción al hacer clic en el ícono de búsqueda */ },
                onProfileClick = { /* Acción al hacer clic en el ícono de cuenta */ },
            )
        }
    ){innerPadding ->
        Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally){
            Perfil()
        }
    }
}

@Preview
@Composable
fun PerfilPagePreview(){
    PerfilPage()
}