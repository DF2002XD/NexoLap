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
 * Un Composable que representa la pantalla principal para el registro de usuarios.
 *
 * Esta pantalla utiliza un [Scaffold] para proporcionar una estructura de diseño básica.
 * Dentro del scaffold, centra un componente [Registro], que contiene los campos del
 * formulario de registro y la lógica asociada.
 *
 * @param modifier Un [Modifier] para ser aplicado al layout. El valor por defecto es [Modifier].
 */
@Composable
fun RegistroPage(modifier: Modifier = Modifier){
    Scaffold{innerPadding ->
        Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally){
            Registro(onClick = { /*TODO*/ })
        }
    }
}

@Preview
@Composable
fun RegistroPagePreview(){
    RegistroPage()
}