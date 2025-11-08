package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.Column
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
import com.example.nexolap.Data.ordenadores

class ListData(var title: String)


/**
 * Una función Composable que muestra una lista vertical de productos.
 * La lista se puede filtrar en función de una consulta de búsqueda.
 *
 * @param searchQuery Una cadena de texto utilizada para filtrar la lista de productos por nombre. Si la consulta está vacía, se muestran todos los productos.
 */
@Composable
fun ListVertical(searchQuery: String = "") {
    val productos = ordenadores
    val productosFiltrados = if (searchQuery.isEmpty()) {
        productos
    } else {
        productos.filter { it.nombre.contains(searchQuery, ignoreCase = true) }
    }
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
        items(productosFiltrados) { producto ->
            TarjetaHorizontal(cardData = producto, onClick = {})
        }
    }
}


/**
 * Un Composable que muestra una lista horizontal de tarjetas de productos.
 * La lista muestra los primeros 10 productos de una fuente de datos predefinida.
 *
 * @param listData Un objeto [ListData] que contiene el título a mostrar encima de la lista horizontal.
 */
@Composable
fun ListHorizontal(listData: ListData) {
   Column(modifier = Modifier.padding(10.dp)) {
         Text(text = listData.title,  )
         LazyRow() {
              item {
                  for (i in 1..10) {
                      TarjetaVertical(cardData = ordenadores[i], onClick = {})
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
