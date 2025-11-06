package com.example.nexolap.myComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexolap.R

class CardData(var nombre: String, var imagen: Int)
@Composable
fun TarjetaVertical ( cardData: CardData) {
    Column(modifier = Modifier.width(120.dp) .clickable{}) {
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

@Composable
fun TarjetaHorizontal (cardData : CardData) {
    //quiero añadir onclick

    Row(modifier = Modifier.width(250.dp).clickable{

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
    TarjetaVertical(cardData = CardData("Ejemplo vertical dadqdqwdqwdqwdqwdqwdasdqdqwdqw" , R.drawable.ic_launcher_foreground))
}

@Preview
@Composable
fun TarjetaHorizontalPreview() {
    TarjetaHorizontal(cardData = CardData("Ejemplo horizontal" , R.drawable.ic_launcher_foreground))
}