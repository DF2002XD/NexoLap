package com.example.nexolap.myComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppTitle(title : String) {
    TopAppBar(
        title = { Text(text = title)}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppTitle1(title: String) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFDFFBFF), // tono claro izquierda
            Color(0xFFD2B3FF)  // tono morado derecha
        )
    )
    val shape = RoundedCornerShape(bottomStart = 18.dp, bottomEnd = 18.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(brush = gradient, shape = shape)
            .border(width = 2.dp, color = Color(0xFF8A4BFF), shape = shape)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            modifier = Modifier.fillMaxSize()
        )
    }
}