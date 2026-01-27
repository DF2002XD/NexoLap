package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexolap.Data.ListaData
import com.example.nexolap.Data.ordenadores
import kotlin.collections.filter

/**
 * Un composable que muestra una lista vertical de productos (ordenadores).
 * La lista puede ser filtrada mediante una consulta de búsqueda.
 * Utiliza un `LazyColumn` para mostrar eficientemente los elementos `TarjetaHorizontal`.
 *
 * @param searchQuery Una cadena de texto utilizada para filtrar la lista de productos.
 *                    La búsqueda no distingue entre mayúsculas y minúsculas y se realiza
 *                    en el nombre del producto. Si está en blanco, se muestran todos los productos.
 */
@Composable
fun ListVertical(searchQuery: String = "") {
//    val productos = ordenadores
//
//    // --- INICIO DE LA CORRECCIÓN ---
//    val productosFiltrados = remember(searchQuery, productos) {
//        if (searchQuery.isBlank()) {
//            productos
//        } else {
//            productos.filter {
//                it.nombre.contains(searchQuery, ignoreCase = true)
//            }
//        }
//    }

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
        items(ordenadores) { producto ->
            TarjetaHorizontal(ordenador = producto, onClick = {})
        }
    }
}

/**
 * Un composable que muestra una lista horizontal de elementos.
 * Consiste en un título y una `LazyRow` que contiene varias tarjetas `TarjetaVertical`.
 *
 * @param listaData Un objeto de tipo [ListaData] que contiene los datos para la lista,
 *                  incluyendo el título que se mostrará encima de la fila horizontal.
 */
@Composable
fun ListHorizontal(listaData: ListaData) {
   Column(modifier = Modifier.padding(10.dp)) {
         Text(text = listaData.title)
         LazyRow() {
              item {
                  for (i in 1..10) {
                      TarjetaVertical(ordenador = ordenadores[i], onClick = {})
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
    ListHorizontal(listaData = ListaData("Lista Vertical"))
}
