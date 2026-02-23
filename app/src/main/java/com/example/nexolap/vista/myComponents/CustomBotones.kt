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
 * Función composable que crea un botón personalizado con un ancho y alto específicos.
 *
 * @param nombre El texto que se mostrará dentro del botón.
 * @param onClick La función de callback que se ejecutará al hacer clic en el botón.
 * @param enabled Controla el estado habilitado del botón; si es falso, el botón no responderá a la interacción.
 */
@Composable
fun Boton(nombre: String, onClick: () -> Unit, enabled: Boolean) {
    Button(onClick = onClick, modifier = Modifier
        .width(300.dp)
        .height(50.dp), enabled = enabled) {
        Text(text = nombre)
    }
}

@Preview
@Composable
fun BotonPreview() {
    Boton("Hola", {}, false)
}