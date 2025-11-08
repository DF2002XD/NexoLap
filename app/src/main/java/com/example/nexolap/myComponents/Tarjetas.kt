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
import com.example.nexolap.R

/**
 * Clase de datos que representa la información necesaria para una tarjeta.
 *
 * Esta clase encapsula los datos que se utilizarán para poblar los componentes
 * de tarjeta, como `TarjetaVertical` y `TarjetaHorizontal`.
 *
 * @property nombre El texto o título que se mostrará en la tarjeta.
 * @property imagen El ID del recurso drawable (por ejemplo, `R.drawable.mi_imagen`) que se usará como imagen en la tarjeta.
 */
class CardData(var nombre: String, var imagen: Int)


/**
 * Un componente Composable que muestra una tarjeta vertical con una imagen y un texto.
 *
 * Esta tarjeta está diseñada para ser clicable. Para evitar clics múltiples o rápidos
 * que podrían causar comportamientos no deseados (como navegar varias veces),
 * el clic se deshabilita después del primer toque.
 *
 * @param cardData Una instancia de [CardData] que contiene la información a mostrar,
 *                 específicamente el [CardData.nombre] (texto) y la [CardData.imagen] (recurso drawable).
 * @param onClick La función lambda que se ejecutará cuando se haga clic en la tarjeta.
 */
@Composable
fun TarjetaVertical ( cardData: CardData, onClick: () -> Unit) {
    var clicado by remember { mutableStateOf(true) }
    Column(modifier = Modifier.width(120.dp) .clickable(enabled = clicado){
        clicado = false
        onClick()
    }) {
        Image(
            painter = painterResource(id = cardData.imagen),
            contentDescription = "Play Button",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Column{
            Text(text = cardData.nombre, textAlign = TextAlign.Center)
        }
    }
}


/**
 * Un componente Composable que muestra una tarjeta horizontal con una imagen y un texto.
 *
 * Esta tarjeta está diseñada para ser clicable. Para evitar clics múltiples o rápidos
 * que podrían causar comportamientos no deseados (como navegar varias veces),
 * el clic se deshabilita después del primer toque.
 *
 * @param cardData Una instancia de [CardData] que contiene la información a mostrar,
 *                 específicamente el [CardData.nombre] (texto) y la [CardData.imagen] (recurso drawable).
 * @param onClick La función lambda que se ejecutará cuando se haga clic en la tarjeta.
 */
@Composable
fun TarjetaHorizontal (cardData : CardData, onClick: () -> Unit) {
    var clicado by remember { mutableStateOf(true) }
    Row(modifier = Modifier.width(250.dp).clickable(enabled = clicado){
        clicado = false
        onClick()
    }){
        Image(
            painter = painterResource(id = cardData.imagen),
            contentDescription = "Play Button",
        )
        Column(Modifier .align(Alignment.CenterVertically)) {
            Text(text = cardData.nombre)
        }
    }
}


@Preview
@Composable
fun TarjetaVerticalPreview() {
    TarjetaVertical(cardData = CardData("Ejemplo vertical dadqdqwdqwdqwdqwdqwdasdqdqwdqw" , R.drawable.ic_launcher_foreground), onClick = {})
}

@Preview
@Composable
fun TarjetaHorizontalPreview() {
    TarjetaHorizontal(cardData = CardData("Ejemplo horizontal" , R.drawable.ic_launcher_foreground), onClick = {})
}