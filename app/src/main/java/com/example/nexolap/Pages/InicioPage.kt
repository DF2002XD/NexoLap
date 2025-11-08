package com.example.nexolap.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.myComponents.InicioSesion

/**
 * Composable que representa la pantalla principal o de inicio de la aplicación.
 * Configura una estructura de diseño básica utilizando un `Scaffold` y centra el
 * componente `InicioSesion`, que probablemente gestiona la interfaz de inicio de sesión del usuario.
 *
 * @param modifier Un `Modifier` para ser aplicado al layout de `InicioPage`. Por defecto es `Modifier`.
 */
@Composable
fun InicioPage(modifier: Modifier = Modifier) {
    Scaffold{innerPadding ->
        Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally){
            InicioSesion()
        }
    }

}

@Preview
@Composable
fun InicioPagePreview() {
    InicioPage()
}