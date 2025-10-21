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

class ListData(var title: String)


@Composable
fun ListHorizontal() {
    LazyColumn{
        item {
            Column {
                for (i in 1..20) {
                    TarjetaHorizontal()
                }
            }

            }
        }
    }

@Composable
fun ListVertical(listData: ListData) {
   Column(modifier = Modifier.padding(10.dp)) {
         Text(text = listData.title, modifier = Modifier.align(Alignment.CenterHorizontally))
         LazyRow() {
              item {
                  for (i in 1..10) {
                      TarjetaVertical(cardData = CardData("Elemento $i"))
                  }
              }
         }
    }
}

@Preview
@Composable
fun PreviewListHorizontal() {
    ListHorizontal()
}

@Preview
@Composable
fun PreviewListVertical() {
    ListVertical(listData = ListData("Lista Vertical"))
}
