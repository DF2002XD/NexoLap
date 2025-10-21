package com.example.nexolap.myComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.R

class CardData(var nombre: String)
@Composable
fun TarjetaVertical ( cardData: CardData) {
    Column {
        Image(
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Play Button"
        )
        Column(Modifier .align(Alignment.CenterHorizontally)) {
            Text(text = cardData.nombre)
        }

    }
}

@Composable
fun TarjetaHorizontal () {
    Row{
        Image(
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Play Button"
        )
        Column(Modifier .align(Alignment.CenterVertically)) {
            Text(text = "Ejemplo Horizontal")
        }
    }
}

@Preview
@Composable
fun TarjetaVerticalPreview() {
    TarjetaVertical(cardData = CardData("Ejemplo"))
}

@Preview
@Composable
fun TarjetaHorizontalPreview() {
    TarjetaHorizontal()
}