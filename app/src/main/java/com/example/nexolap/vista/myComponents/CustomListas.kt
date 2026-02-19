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
import com.example.nexolap.Data.Ordenador
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState1


@Composable
fun ListVertical(
    ordenadores: List<OrdenadorUIState1>,
    onOrdenadorClick: (Int) -> Unit = {}
) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(ordenadores) { producto ->
            TarjetaHorizontal(
                ordenador = Ordenador(producto.id, producto.imagenPrincipal, producto.nombre),
                onClick = { onOrdenadorClick(producto.id) }
            )
        }
    }
}


@Composable
fun ListHorizontal(
    listaData: ListaData,
    ordenadores: List<OrdenadorUIState1>,
    onOrdenadorClick: (Int) -> Unit = {}
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = listaData.title)
        LazyRow {
            items(ordenadores.take(10)) { producto ->
                TarjetaVertical(
                    ordenador = Ordenador(producto.id, producto.imagenPrincipal, producto.nombre),
                    onClick = { onOrdenadorClick(producto.id) }
                )
            }
        }
    }
}
