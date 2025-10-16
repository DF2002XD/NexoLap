package com.example.nexolap.myComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.R

@Composable
fun TarjetaVertical () {
    Column {
        Image(
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Play Button"
        )
        Text(text = "")
    }
}

@Composable
fun TarjetaHorizontal () {
    Row{
        Image(
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Play Button"
        )
        Column {
            Text(text = "")
        }
    }
}

@Preview
@Composable
fun TarjetaVerticalPreview() {
    TarjetaVertical()
}

@Preview
@Composable
fun TarjetaHorizontalPreview() {
    TarjetaHorizontal()
}