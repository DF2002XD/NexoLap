package com.example.nexolap.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nexolap.R
import com.example.nexolap.myComponents.SpecRow
import com.example.nexolap.myComponents.TopToolbar

@Composable
fun DetailPage() {
    Scaffold(
        topBar = { TopToolbar("Detalles") }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.macbook_air_m2), // imagen ya en tu proyecto? si no, cámbiala
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(Modifier.height(20.dp))

            Text("HP Victus 15-fa2005ns", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))

            SpecRow("Pantalla", "15.6” FHD 144Hz")
            SpecRow("Procesador", "Intel i5 13th Gen")
            SpecRow("RAM", "16GB DDR5")
            SpecRow("Almacenamiento", "512GB SSD NVMe")
            SpecRow("Gráficos", "NVIDIA RTX 3050")
            SpecRow("Batería", "6 horas")
        }
    }
}
