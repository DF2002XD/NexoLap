package com.example.nexolap.vista.myComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.nexolap.viewmodel.uistate.EspecificacionUIState
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState


/**
 * Función composable que muestra la información detallada de un ordenador.
 *
 * Este componente presenta una interfaz que incluye la imagen principal del ordenador,
 * su nombre como título y una tabla con su lista de especificaciones técnicas.
 * El contenido permite el desplazamiento vertical (scroll).
 *
 * @param ordenador Estado de la interfaz que contiene los datos básicos del ordenador (nombre e imagen).
 * @param especificaciones Lista de estados de la interfaz que representan las características técnicas a mostrar.
 */
@Composable
fun Detalles(
    ordenador: OrdenadorUIState,
    especificaciones: List<EspecificacionUIState>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ordenador.imagenPrincipal,
            contentDescription = ordenador.nombre,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(200.dp)
        )
        Text(text = ordenador.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))

        TablaEspecificaciones(especificaciones = especificaciones)
    }

}

@Preview
@Composable
fun DetallesPreview() {
    Detalles(
        ordenador = OrdenadorUIState("1", "asa", "R.drawable.macbook_air_m2"),
        especificaciones = listOf(
            EspecificacionUIState("1", "Procesador", "Intel Core i5-12400H")
        )
    )
}