package com.example.nexolap.vista.myComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexolap.viewmodel.uistate.EspecificacionUIState

/**
 * Composable que muestra una tabla de especificaciones técnicas con un diseño de filas alternas.
 *
 * La tabla consta de una cabecera fija ("Especificación" y "Detalle") y una lista de filas
 * generadas dinámicamente a partir de una lista de estados de UI. Cada fila par tiene un
 * fondo ligeramente resaltado para mejorar la legibilidad.
 *
 * @param especificaciones Lista de objetos [EspecificacionUIState] que contienen la información
 * a mostrar en cada fila de la tabla (componente y descripción).
 */
@Composable
fun TablaEspecificaciones(especificaciones: List<EspecificacionUIState>) {
    Column(
        modifier = Modifier.fillMaxWidth() // La tabla ahora ocupa todo el ancho
    ) {
        // --- Fila de la Cabecera ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TableCell(text = "Especificación", weight = 0.4f, isHeader = true)
            TableCell(text = "Detalle", weight = 0.6f, isHeader = true)
        }
        HorizontalDivider()
        // --- Filas de Datos ---
        especificaciones.forEachIndexed { index, especificacion ->
            val backgroundColor = if (index % 2 == 0) {
                // Color de fondo para filas pares (gris claro)
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
            } else {
                // Sin fondo para filas impares
                Color.Transparent
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor) // Aplica el fondo alterno
                    .padding(vertical = 12.dp), // Aumenta el espaciado vertical
                verticalAlignment = Alignment.CenterVertically
            ) {
                TableCell(text = especificacion.componente, weight = 0.4f)
                TableCell(text = especificacion.descripcion, weight = 0.6f)
            }
            HorizontalDivider()
        }
    }
}


/**
 * Dibuja una celda individual dentro de una fila de tabla.
 *
 * Esta función de extensión de [RowScope] utiliza el modificador `weight` para distribuir
 * el espacio horizontal de forma proporcional dentro de un [Row].
 *
 * @param text El contenido de texto que se mostrará en la celda.
 * @param weight El peso proporcional que determina el ancho de la celda respecto a las demás.
 * @param isHeader Indica si la celda debe estilizarse como encabezado (texto en negrita y color resaltado).
 *                 Por defecto es `false`.
 */
@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    isHeader: Boolean = false
) {
    Text(
        text = text,
        modifier = Modifier
            .weight(weight)
            .padding(horizontal = 8.dp), // Solo padding horizontal
        fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal,
        fontSize = 14.sp,
        color = if (isHeader) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Preview
@Composable
fun TablaEspecificacionesPreview() {
    TablaEspecificaciones(
        especificaciones = listOf(
            EspecificacionUIState("1", "Procesador", "Intel Core i5-12400H"),
            EspecificacionUIState("2", "Memoria", "16 GB RAM"),
            EspecificacionUIState("3", "Disco Duro", "512 GB SSD"),
            EspecificacionUIState("4", "Tarjeta Gráfica", "NVIDIA GeForce RTX 3050")
        )
    )
}
