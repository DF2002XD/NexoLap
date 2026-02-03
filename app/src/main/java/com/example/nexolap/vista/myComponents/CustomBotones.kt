package com.example.nexolap.vista.myComponents

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Un composable personalizado que muestra un botón con un texto específico.
 * Este botón tiene un tamaño fijo y su estado (habilitado/deshabilitado) puede ser controlado.
 *
 * @param nombre El texto que se mostrará dentro del botón.
 * @param onClick La función lambda que se ejecutará cuando se haga clic en el botón.
 * @param enabled Un valor booleano que determina si el botón es clicable o no.
 */
@Composable
fun Boton (nombre: String, onClick: () -> Unit, enabled: Boolean){
    Button(onClick = onClick, modifier = Modifier.width(300.dp).height(50.dp), enabled = enabled){
        Text(text = nombre)
    }
}

@Preview
@Composable
fun BotonPreview(){
    Boton("Hola", {}, false)
}