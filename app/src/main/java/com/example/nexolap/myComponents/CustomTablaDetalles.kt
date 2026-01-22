package com.example.nexolap.myComponents

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexolap.Data.Especificacion
import com.example.nexolap.R

/**
 * Un composable que muestra una tabla de especificaciones de producto.
 * La tabla consta de dos columnas: "Especificación" y "Detalle".
 * Incluye una fila de cabecera y colorea las filas de datos de forma alterna para mejorar la legibilidad.
 *
 * @param especificaciones Una lista de objetos [Especificacion] que contienen los datos a mostrar.
 *                         Cada objeto representa una fila en la tabla.
 */
@Composable
fun TablaEspecificaciones(especificaciones: List<Especificacion>) {
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
            TableCell(text = stringResource(R.string.especificaciones), weight = 0.4f, isHeader = true)
            TableCell(text = stringResource(R.string.detalles), weight = 0.6f, isHeader = true)
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
                TableCell(text = especificacion.nombre, weight = 0.4f)
                TableCell(text = especificacion.detalle, weight = 0.6f)
            }
            HorizontalDivider()
        }
    }
}

/**
 * Un composable que representa una única celda dentro de una fila de una tabla.
 * Muestra texto y su apariencia se puede personalizar para celdas de encabezado o de datos.
 * Este composable debe usarse dentro de un [RowScope], normalmente como hijo de un [Row].
 *
 * @param text El contenido de texto que se mostrará en la celda.
 * @param weight El ancho relativo de la celda dentro de la fila. Determina cuánto espacio horizontal ocupa la celda.
 * @param isHeader Una bandera booleana para indicar si esta celda es parte del encabezado de la tabla.
 *                 Si es verdadero, el texto se mostrará en negrita y con un color diferente para distinguirlo. El valor por defecto es falso.
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
fun TablaEspecificacionesPreview(){
    TablaEspecificaciones(
        especificaciones = listOf(
            Especificacion("Especificacion 1", "Detalle 1"),
            Especificacion("Especificacion 2", "Detalle 2"),
            Especificacion("Especificacion 3", "Detalle 3")
        )
    )
}
