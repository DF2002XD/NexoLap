package com.example.nexolap.myComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexolap.Data.Ordenador
import com.example.nexolap.R


/**
 * Un Composable que muestra una tarjeta vertical para un ordenador (`Ordenador`).
 * La tarjeta consiste en una imagen en la parte superior y el nombre del ordenador debajo.
 * Toda la tarjeta es clicable.
 *
 * @param ordenador El objeto de datos [Ordenador] que se mostrará en la tarjeta. Contiene
 *   el nombre y el ID del recurso para la imagen principal.
 * @param onClick Una función lambda que se ejecutará cuando se haga clic en la tarjeta.
 */
@Composable
fun TarjetaVertical (ordenador: Ordenador, onClick: () -> Unit) {
    var clicado by remember { mutableStateOf(true) }
    Column(modifier = Modifier
        .width(120.dp)
        .clickable(enabled = clicado) {
            onClick()
        }) {
        Image(
            painter = painterResource(id = ordenador.imagenPrincipal),
            contentDescription = "Play Button",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Column{
            Text(text = ordenador.nombre, textAlign = TextAlign.Center)
        }
    }
}


/**
 * Un Composable que muestra una tarjeta horizontal para un ordenador (`Ordenador`).
 * La tarjeta consiste en una imagen a la izquierda y el nombre del ordenador a la derecha.
 * Toda la tarjeta es clicable.
 *
 * @param ordenador El objeto de datos [Ordenador] que se mostrará en la tarjeta. Contiene
 *   el nombre y el ID del recurso para la imagen principal.
 * @param onClick Una función lambda que se ejecutará cuando se haga clic en la tarjeta.
 */
@Composable
fun TarjetaHorizontal (ordenador: Ordenador, onClick: () -> Unit) {
    var clicado by remember { mutableStateOf(true) }
    Row(modifier = Modifier
        .width(250.dp)
        .clickable(enabled = clicado) {
            onClick()
        }){
        Image(
            painter = painterResource(id = ordenador.imagenPrincipal),
            contentDescription = "Play Button",
        )
        Column(Modifier .align(Alignment.CenterVertically)) {
            Text(text = ordenador.nombre)
        }
    }
}


@Preview
@Composable
fun TarjetaVerticalPreview() {
    TarjetaVertical(
        ordenador = Ordenador(
            1,
            nombre = "Apple MacBook Air 13\" (M2)",
            imagenPrincipal = R.drawable.macbook_air_m2,
            especificaciones = emptyList()
        ),
        onClick = { TODO() }
    )
}

@Preview (showBackground = true)
@Composable
fun TarjetaHorizontalPreview() {
    TarjetaHorizontal(ordenador = Ordenador(
        1,
        nombre = "Apple MacBook Air 13\" (M2)",
        imagenPrincipal = R.drawable.macbook_air_m2,
        especificaciones = emptyList()
    ), onClick = {TODO()})
}