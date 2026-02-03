package com.example.nexolap.vista.myComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexolap.R

/**
 * Un campo de texto personalizable para introducir contraseñas.
 *
 * Este componente incluye una etiqueta, un campo de texto y un icono para alternar la visibilidad de la contraseña.
 * El estado de visibilidad de la contraseña se gestiona internamente.
 *
 * @param label El texto que se mostrará como etiqueta encima del campo de texto.
 * @param valor El valor actual del campo de contraseña.
 * @param onValorCambiado El callback que se invoca cuando el valor del campo de texto cambia.
 * @param modifier El [Modifier] que se aplicará al componente `TextField`. Por defecto es un [Modifier] vacío.
 * @param esError Indica si el campo de texto debe mostrarse en estado de error. Por defecto es `false`.
 */
@Composable
fun CampoContrasenha(
    label: String,
    valor: String,
    onValorCambiado: (String) -> Unit,
    modifier: Modifier = Modifier,
    esError: Boolean = false
) {
    // Estado para controlar la visibilidad, interno a este componente
    var esVisible by remember { mutableStateOf(false) }

    Text(text = label, modifier = Modifier.padding(bottom = 10.dp), fontSize = 25.sp)
    TextField(
        value = valor,
        onValueChange = onValorCambiado,
        modifier = modifier.fillMaxWidth(),
        isError = esError,
        visualTransformation = if (esVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icono = if (esVisible) painterResource(id = R.drawable.outline_visibility_24) else painterResource(id = R.drawable.outline_visibility_off_24)
            val descripcion = if (esVisible) "Ocultar contraseña" else "Mostrar contraseña"
            IconButton(onClick = { esVisible = !esVisible }) {
                Icon(painter = icono, contentDescription = descripcion)
            }
        }
    )
}

@Preview
@Composable
fun CampoContrasenhaPreview() {
    CampoContrasenha(label = "Contraseña", valor = "", onValorCambiado = {})
}
