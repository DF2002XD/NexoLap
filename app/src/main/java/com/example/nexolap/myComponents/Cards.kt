package com.example.nexolap.myComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.R

class CardData(var nombre: String, var imagen: Int)
@Composable
fun TarjetaVertical ( cardData: CardData) {
    Column {
        Image(
            painter = painterResource(id = cardData.imagen),
            contentDescription = "Play Button"
        )
        Column(Modifier .align(Alignment.CenterHorizontally)) {
            Text(text = cardData.nombre)
        }
        TextField(
            value = String(),
            label = { Text("Nuevo Producto") },
            onValueChange = {String()},
            singleLine = true
        )
    }
}

@Composable
fun TarjetaHorizontal (cardData : CardData) {
    Row{
        Image(
            painter = painterResource(id = cardData.imagen),
            contentDescription = "Play Button"
        )
        Column(Modifier .align(Alignment.CenterVertically)) {
            Text(text = cardData.nombre)
        }

    }
}

@Preview
@Composable
fun TarjetaVerticalPreview() {
    TarjetaVertical(cardData = CardData("Ejemplo vertical" , R.drawable.ic_launcher_foreground))
}

@Preview
@Composable
fun TarjetaHorizontalPreview() {
    TarjetaHorizontal(cardData = CardData("Ejemplo horizontal" , R.drawable.ic_launcher_foreground))
}