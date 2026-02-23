package com.example.nexolap.vista.myComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nexolap.Data.ListaData
import com.example.nexolap.Data.OrdenadorRetrofit
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState

/**
 * Función composable que muestra una lista vertical de ordenadores utilizando un [LazyColumn].
 * Cada elemento de la lista se representa mediante el componente [TarjetaHorizontal].
 *
 * @param ordenadores Lista de objetos [OrdenadorUIState] que se van a mostrar.
 * @param onOrdenadorClick Función de callback que se ejecuta al hacer clic en un elemento,
 * recibiendo el ID del ordenador como un [String].
 */
@Composable
fun ListVertical(
    ordenadores: List<OrdenadorUIState>,
    onOrdenadorClick: (String) -> Unit = {}
) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(ordenadores) { producto ->
            TarjetaHorizontal(
                ordenador = OrdenadorRetrofit(
                    producto.id,
                    producto.nombre,
                    producto.imagenPrincipal
                ),
                onClick = { onOrdenadorClick(producto.id) }
            )
        }
    }
}

/**
 * Muestra una lista horizontal de ordenadores con un título.
 *
 * Esta función compone una fila desplazable (LazyRow) que muestra hasta un máximo de 10
 * elementos representados por [TarjetaVertical].
 *
 * @param listaData Objeto que contiene la información de la lista, como el título a mostrar.
 * @param ordenadores Lista de estados de interfaz de usuario ([OrdenadorUIState]) que se mostrarán.
 * @param onOrdenadorClick Función de callback que se ejecuta cuando se hace clic en un ordenador,
 * recibiendo su ID como parámetro.
 */
@Composable
fun ListHorizontal(
    listaData: ListaData,
    ordenadores: List<OrdenadorUIState>,
    onOrdenadorClick: (String) -> Unit = {}
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = listaData.title)
        LazyRow {
            items(ordenadores.take(10)) { producto ->
                TarjetaVertical(
                    ordenador = OrdenadorRetrofit(
                        producto.id,
                        producto.nombre,
                        producto.imagenPrincipal
                    ),
                    onClick = { onOrdenadorClick(producto.id) }
                )
            }
        }
    }
}
