package com.example.nexolap.Pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.myComponents.ListData
import com.example.nexolap.myComponents.ListVertical
import com.example.nexolap.myComponents.TopAppTitle
import com.example.nexolap.myComponents.TopAppTitle1

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppTitle1(
                title = "NexoLap"
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                ListVertical(listData = ListData("Más Vendidos"))
            }
            item {
                ListVertical(listData = ListData("Populares"))
            }
            item {
                ListVertical(listData = ListData("Nuevos Lanzamientos"))
            }
        }
    }
}


@Preview
@Composable
fun PreviewHomePage() {
    HomePage()
}