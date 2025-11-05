package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardProfile(
    username: String,
    email: String,
    onUpdateEmail: () -> Unit,
    onUpdatePassword: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text("Usuario", style = MaterialTheme.typography.labelMedium)
            Text(username, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(10.dp))

            Text("Correo electrónico", style = MaterialTheme.typography.labelMedium)
            Text(email, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(20.dp))

            Button(
                onClick = onUpdateEmail,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Actualizar correo") }

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = onUpdatePassword,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Actualizar contraseña") }
        }
    }
}
