package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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