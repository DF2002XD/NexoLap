package com.example.nexolap.vista.myComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.nexolap.modelo.OrdenadorDTO


/**
 * Componente que muestra una tarjeta con disposición vertical para un ordenador.
 * Incluye la imagen principal y el nombre del dispositivo debajo de la misma.
 *
 * @param ordenador Objeto de tipo [OrdenadorDTO] que contiene la información del dispositivo a mostrar.
 * @param onClick Acción que se ejecutará al pulsar sobre la tarjeta.
 */
@Composable
fun TarjetaVertical(ordenador: OrdenadorDTO, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .clickable {
                onClick()
            }) {
        AsyncImage(
            model = ordenador.imagenPrincipal,
            contentDescription = "Imagen de ${ordenador.nombre}",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Fit
        )
        Column {
            Text(
                text = ordenador.nombre,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(120.dp)
            )
        }
    }
}


/**
 * Componente que muestra la información de un ordenador en un formato de tarjeta horizontal.
 *
 * @param ordenador El objeto [OrdenadorDTO] que contiene la información del equipo a mostrar.
 * @param onClick Acción que se ejecutará al hacer clic en la tarjeta.
 */
@Composable
fun TarjetaHorizontal(ordenador: OrdenadorDTO, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .width(250.dp)
            .clickable {
                onClick()
            }) {
        AsyncImage(
            model = ordenador.imagenPrincipal,
            contentDescription = "Imagen de ${ordenador.nombre}",
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(Modifier.align(Alignment.CenterVertically)) {
            Text(text = ordenador.nombre)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TarjetaVerticalPreview() {
    TarjetaVertical(
        ordenador = OrdenadorDTO(
            "1",
            nombre = "Apple MacBook Air 13\" (M2)",
            imagenPrincipal = "https://example.com/image.jpg"
        ), onClick = { })
}

@Preview(showBackground = true)
@Composable
fun TarjetaHorizontalPreview() {
    TarjetaHorizontal(
        ordenador = OrdenadorDTO(
            "1",
            nombre = "Apple MacBook Air 13\" (M2)",
            imagenPrincipal = "https://example.com/image.jpg"
        ), onClick = { })
}
