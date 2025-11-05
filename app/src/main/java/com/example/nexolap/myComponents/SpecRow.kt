package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpecRow(title: String, value: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(title, modifier = Modifier.weight(1f))
        Text(value, modifier = Modifier.weight(1f))
    }
}
