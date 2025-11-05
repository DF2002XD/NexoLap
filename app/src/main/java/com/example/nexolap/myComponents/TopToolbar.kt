package com.example.nexolap.myComponents

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopToolbar(title: String) {
    TopAppBar(title = { Text(title) })
}
