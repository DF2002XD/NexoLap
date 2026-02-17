package com.example.nexolap.vista.myComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexolap.Data.ListaData
import com.example.nexolap.Data.Ordenador
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState1


@Composable
fun ListVertical(
    ordenadores: List<OrdenadorUIState1>,
    ) {

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
        items(ordenadores) { producto ->
            TarjetaHorizontal(ordenador = Ordenador(producto.id, producto.imagenPrincipal, producto.nombre), onClick = {})
        }
    }
}


@Composable
fun ListHorizontal(listaData: ListaData,
                   ordenadores: List<OrdenadorUIState1>) {
   Column(modifier = Modifier.padding(10.dp)) {
         Text(text = listaData.title)
         LazyRow() {
              item {
                  for (producto in ordenadores.take(10)) {
                      TarjetaVertical(ordenador = Ordenador(producto.id, producto.imagenPrincipal, producto.nombre), onClick = {})
                  }
              }
         }
    }
}

@Preview
@Composable
fun PreviewListVertical() {
    ListVertical( ordenadores = emptyList())
}

@Preview
@Composable
fun PreviewListHorizontal() {
    ListHorizontal(listaData = ListaData("Lista Vertical"), ordenadores = emptyList())
}