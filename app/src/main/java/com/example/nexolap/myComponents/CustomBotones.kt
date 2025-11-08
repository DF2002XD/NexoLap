package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Un composable personalizado que renderiza un botón con un tamaño y texto específicos.
 *
 * Este botón tiene un ancho fijo de 300.dp y una altura fija de 50.dp.
 *
 * @param nombre El texto que se mostrará dentro del botón.
 * @param onClick Una función lambda que se ejecutará cuando se haga clic en el botón.
 */
@Composable
fun Boton (nombre : String, onClick : () -> Unit){
    Button(onClick = onClick, modifier = Modifier.width(300.dp).height(50.dp)){
        Text(text = nombre)
    }
}

@Preview
@Composable
fun BotonPreview(){
    Boton("Hola", {})
}