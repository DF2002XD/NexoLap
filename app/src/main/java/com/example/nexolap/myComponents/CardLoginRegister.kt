package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardLoginRegister(
    title: String,
    description: String? = null,
    fields: @Composable ColumnScope.() -> Unit,
    buttonText: String,
    onButtonClick: () -> Unit,
    footerText: String? = null,
    onFooterClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, style = MaterialTheme.typography.titleLarge)

            if (description != null) {
                Spacer(Modifier.height(8.dp))
                Text(description, style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(Modifier.height(16.dp))
            fields()

            Spacer(Modifier.height(18.dp))
            Button(
                onClick = onButtonClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(buttonText)
            }

            if (footerText != null && onFooterClick != null) {
                Spacer(Modifier.height(12.dp))
                TextButton(onClick = onFooterClick) {
                    Text(footerText)
                }
            }
        }
    }
}
