package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Un Composable que muestra una barra de búsqueda.
 *
 * Este componente proporciona una interfaz de usuario para ingresar consultas de búsqueda. Incluye
 * un ícono de búsqueda, un botón para limpiar el texto que aparece cuando se introduce algo, y una
 * vista expandible para mostrar resultados de búsqueda o sugerencias.
 *
 * El estado de la consulta de búsqueda y el estado de expansión/colapso de la barra
 * se manejan internamente utilizando `remember`.
 *
 * Se utiliza `@OptIn` para [ExperimentalMaterial3Api] porque [SearchBar] aún es una API
 * experimental en Material Design 3.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Buscador(){
    var searchQuery by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { expanded = false },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear search")
                        }
                    }
                },
                //shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = Modifier.fillMaxWidth()
    ) {
        // Resultados de búsqueda rápida
        if (searchQuery.isNotEmpty()) {
            // Aquí puedes mostrar resultados rápidos
            Text("Buscando: $searchQuery", modifier = Modifier.padding(16.dp))
        }
    }
}