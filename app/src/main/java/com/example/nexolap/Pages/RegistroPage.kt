package com.example.nexolap.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.myComponents.Registro


/**
 * Función composable que representa la pantalla de registro de la aplicación.
 *
 * Esta pantalla proporciona la interfaz de usuario para registrar una nueva cuenta. Utiliza un [Scaffold]
 * para proveer una estructura de diseño básica y centra el componente [Registro], que contiene
 * los campos del formulario de registro y la lógica asociada.
 *
 * @param modifier El [Modifier] que se aplicará al layout. Por defecto es [Modifier].
 */
@Composable
fun RegistroPage(modifier: Modifier = Modifier){
    Scaffold{innerPadding ->
        Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally){
            Registro(onRegisterClicked = {}, onNavigateToLogin = {})
        }

    }
}

@Preview
@Composable
fun RegistroPagePreview(){
    RegistroPage()
}