package com.example.nexolap.vista.myComponents

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexolap.R
import com.example.nexolap.viewmodel.uistate.OrdenadorUIState1
import com.example.nexolap.viewmodel.uistate.especificacionUIState1


@Composable
fun Detalles(
    ordenador: OrdenadorUIState1,
    especificaciones: List<especificacionUIState1>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = ordenador.imagenPrincipal),
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
        ordenador = OrdenadorUIState1(1, "asa", R.drawable.macbook_air_m2),
        especificaciones = listOf(
            especificacionUIState1(1, "Procesador", "Intel Core i5-12400H")
        )
    )
}