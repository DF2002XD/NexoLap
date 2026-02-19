package com.example.nexolap.vista.myComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexolap.R

@Composable
fun Perfil(
    nombreUsuarioInicial: String,
    colorFondo: Color = Color.Gray,
    onActualizarNombre: (String) -> Unit = {},
    onActualizarCorreo: (String) -> Unit = {},
    onActualizarContrasenha: (String) -> Unit = {},
    onActualizarColor: () -> Unit = {},
    onEliminarCuenta: () -> Unit = {}
) {
    var nombreUsuario by remember { mutableStateOf(nombreUsuarioInicial) }
    var nuevoCorreo by remember { mutableStateOf("") }
    var nuevaContrasenha by remember { mutableStateOf("") }
    var repetirContrasenha by remember { mutableStateOf("") }
    var isEditingNombre by remember { mutableStateOf(false) }
    val contrasenhasCoinciden = nuevaContrasenha == repetirContrasenha

    // Sincronizamos el nombre con el valor del ViewModel
    LaunchedEffect(nombreUsuarioInicial) {
        nombreUsuario = nombreUsuarioInicial
    }

    val inicial = if (nombreUsuario.isNotEmpty()) nombreUsuario.take(1).uppercase() else "?"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier.padding(bottom = 16.dp)) {
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(colorFondo),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = inicial,
                    color = Color.White,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(
                onClick = onActualizarColor,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 10.dp, y = 10.dp)
                    .background(
                        MaterialTheme.colorScheme.surface,
                        CircleShape
                    )
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Cambiar color de perfil")
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Spacer(modifier = Modifier.width(48.dp))

            if (isEditingNombre) {
                TextField(
                    value = nombreUsuario,
                    onValueChange = { nombreUsuario = it },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    modifier = Modifier.width(200.dp)
                )
            } else {
                Text(
                    text = nombreUsuario,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }

            IconButton(
                onClick = {
                    if (isEditingNombre) {
                        onActualizarNombre(nombreUsuario)
                    }
                    isEditingNombre = !isEditingNombre
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = if (isEditingNombre) Icons.Default.Check else Icons.Default.Edit,
                    contentDescription = if (isEditingNombre) "Guardar nombre" else "Editar nombre"
                )
            }
        }

        Text(
            text = stringResource(R.string.Correo_electronico),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nuevoCorreo,
            onValueChange = { nuevoCorreo = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onActualizarCorreo(nuevoCorreo)
                nuevoCorreo = ""
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = nuevoCorreo.isNotEmpty()
        ) {
            Text(text = stringResource(R.string.Actualizar_correo))
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.contrasenha),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoContrasenha(
            valor = nuevaContrasenha,
            onValorCambiado = { nuevaContrasenha = it },
            label = stringResource(R.string.Nueva_contrasenha)
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoContrasenha(
            valor = repetirContrasenha,
            onValorCambiado = { repetirContrasenha = it },
            label = stringResource(R.string.Repetir_contrasenha),
            esError = !contrasenhasCoinciden && repetirContrasenha.isNotEmpty()
        )

        if (!contrasenhasCoinciden && repetirContrasenha.isNotEmpty()) {
            Text(
                text = stringResource(R.string.contrasenhas_no_coinciden),
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp),
                textAlign = TextAlign.Start,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Boton(
            stringResource(R.string.Actualizar_contrasenha),
            onClick = {
                onActualizarContrasenha(nuevaContrasenha)
                nuevaContrasenha = ""
                repetirContrasenha = ""
            },
            enabled = contrasenhasCoinciden && nuevaContrasenha.isNotEmpty()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Boton(
            stringResource(R.string.Eliminar_cuenta),
            onClick = { onEliminarCuenta() },
            enabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilPreview() {
    Perfil(nombreUsuarioInicial = "Usuario de Prueba")
}
