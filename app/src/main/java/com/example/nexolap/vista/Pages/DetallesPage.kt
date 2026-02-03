package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.vista.myComponents.Detalles

/**
 * Un composable que representa la pantalla de detalles de un ordenador específico.
 *
 * Esta pantalla actúa como un contenedor para el componente `Detalles`, proporcionando una
 * estructura de diseño básica con un `Scaffold`. Recibe el ID del ordenador que se
 * debe mostrar y lo pasa al componente `Detalles`, que es el responsable de obtener
 * y renderizar los datos concretos.
 *
 * @param modifier Un [Modifier] opcional para ser aplicado al layout.
 * @param ordenadorId El identificador único del ordenador cuyos detalles se van a mostrar.
 */
@Composable
fun DetallesPage(modifier: Modifier = Modifier, ordenadorId: Int){
    Scaffold(

    ){innerPadding ->
        Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally){
            Detalles(ordenadorId = ordenadorId)
        }
    }
}

@Preview
@Composable
fun DetallesPagePreview(){
    DetallesPage(ordenadorId = 1)

}