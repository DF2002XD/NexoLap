package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexolap.R

class ListData(var title: String)


@Composable
fun ListVertical() {
    LazyColumn{
        item {
            Column {
                for (i in 1..20) {
                    TarjetaHorizontal( cardData = CardData("Elemento $i" ,  R.drawable.ic_launcher_foreground) )
                }
            }

            }
        }
    }

@Composable
fun ListHorizontal(listData: ListData) {
   Column(modifier = Modifier.padding(10.dp)) {
         Text(text = listData.title, modifier = Modifier.align(Alignment.CenterHorizontally))
         LazyRow() {
              item {
                  for (i in 1..10) {
                      TarjetaVertical(cardData = CardData("Elemento $i" ,  R.drawable.ic_launcher_foreground))
                  }
              }
         }
    }
}

@Preview
@Composable
fun PreviewListVertical() {
    ListVertical()
}

@Preview
@Composable
fun PreviewListHorizontal() {
    ListHorizontal(listData = ListData("Lista Vertical"))
}
