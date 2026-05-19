package com.example.nexolap.vista.myComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexolap.data.UsuarioApi
import com.example.nexolap.R


/**
 * Función composable que muestra la interfaz de inicio de sesión.
 *
 * @param keepLogged Booleano que indica si la casilla de "mantener sesión iniciada" está marcada.
 * @param onKeepLoggedChange Callback que se dispara cuando cambia el estado de la casilla de mantener sesión.
 * @param errorMessage Mensaje de error opcional que se muestra en caso de un intento de inicio de sesión fallido.
 * @param onLoginClicked Callback que se dispara al pulsar el botón de inicio de sesión, proporcionando el correo y la contraseña ingresados.
 * @param onNavigateToRegister Callback para navegar a la pantalla de registro.
 */
@Composable
fun LoginSesion(
    keepLogged: Boolean,
    onKeepLoggedChange: (Boolean) -> Unit,
    errorMessage: String? = null,
    onLoginClicked: (correo: String, contrasenha: String) -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var contrasenha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = stringResource(R.string.inicio_de_sesion),
            fontSize = 40.sp,
            modifier = Modifier.padding(bottom = 60.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.correo),
                modifier = Modifier.padding(bottom = 10.dp),
                fontSize = 25.sp,
            )
            TextField(
                value = correo,
                onValueChange = { correo = it },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CampoContrasenha(
                label = stringResource(R.string.contrasenha),
                valor = contrasenha,
                onValorCambiado = { contrasenha = it }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = keepLogged,
                onCheckedChange = onKeepLoggedChange
            )
            Text(text = "Mantener sesión iniciada", fontSize = 16.sp)
        }

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Boton(
                nombre = stringResource(R.string.inicio_sesion),
                onClick = { onLoginClicked(correo, contrasenha) },
                enabled = correo.isNotBlank() && contrasenha.isNotBlank()
            )

            Spacer(modifier = Modifier.height(26.dp))

            Boton(
                nombre = stringResource(R.string.registrarse),
                onClick = onNavigateToRegister,
                enabled = true
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}


/**
 * Función composable que muestra la interfaz de registro de nuevos usuarios.
 *
 * @param usuario Objeto que contiene los datos actuales del usuario en el formulario (nombre, correo, contraseña).
 * @param repitaContrasenha Valor del campo para confirmar la contraseña.
 * @param onNombreChange Callback que se dispara cuando cambia el valor del nombre.
 * @param onCorreoChange Callback que se dispara cuando cambia el valor del correo electrónico.
 * @param onContrasenhaChange Callback que se dispara cuando cambia el valor de la contraseña.
 * @param onRepitaContrasenhaChange Callback que se dispara cuando cambia el valor de la confirmación de contraseña.
 * @param onRegisterClicked Callback que se dispara al pulsar el botón de crear cuenta.
 * @param onNavigateToLogin Callback para navegar a la pantalla de inicio de sesión.
 */
@Composable
fun Registro(
    usuario: UsuarioApi,
    repitaContrasenha: String,
    onNombreChange: (String) -> Unit,
    onCorreoChange: (String) -> Unit,
    onContrasenhaChange: (String) -> Unit,
    onRepitaContrasenhaChange: (String) -> Unit,
    onRegisterClicked: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val contrasenhasCoinciden = usuario.contrasenha == repitaContrasenha

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(top = 80.dp, bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.crear_nueva_cuenta),
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )
            val annotatedText = buildAnnotatedString {
                append(stringResource(R.string.ya_esta_registrado))
                append(" ")
                val start = length
                append(stringResource(R.string.login_aqui))
                addStyle(
                    style = SpanStyle(color = Color.Blue),
                    start = start,
                    end = length
                )
                addLink(
                    clickable = LinkAnnotation.Clickable(
                        tag = "LOGIN",
                        linkInteractionListener = { _ -> onNavigateToLogin() }
                    ),
                    start = start,
                    end = length
                )
            }
            Text(text = annotatedText, modifier = Modifier.padding(top = 10.dp), fontSize = 16.sp)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.nombre),
                modifier = Modifier.padding(bottom = 10.dp),
                fontSize = 25.sp
            )
            TextField(
                value = usuario.nombre,
                onValueChange = onNombreChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.correo),
                modifier = Modifier.padding(bottom = 10.dp),
                fontSize = 25.sp
            )
            TextField(
                value = usuario.correo,
                onValueChange = onCorreoChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CampoContrasenha(
                valor = usuario.contrasenha,
                onValorCambiado = onContrasenhaChange,
                label = stringResource(R.string.contrasenha)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CampoContrasenha(
                valor = repitaContrasenha,
                onValorCambiado = onRepitaContrasenhaChange,
                label = stringResource(R.string.repita_contrasenha),
                esError = !contrasenhasCoinciden && repitaContrasenha.isNotEmpty()
            )
            if (!contrasenhasCoinciden && repitaContrasenha.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.contrasenhas_no_coinciden),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))

        Boton(
            nombre = stringResource(R.string.crear_cuenta),
            onClick = onRegisterClicked,
            enabled = contrasenhasCoinciden && usuario.nombre.isNotBlank() && usuario.correo.isNotBlank() && usuario.contrasenha.isNotBlank()
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
fun PreviewAcceso() {
    LoginSesion(
        keepLogged = false,
        onKeepLoggedChange = {},
        onLoginClicked = { _, _ -> },
        onNavigateToRegister = {}
    )
}

@Preview
@Composable
fun PreviewRegistro() {
    Registro(
        usuario = UsuarioApi("0", "", "", "", false, 0),
        repitaContrasenha = "",
        onNombreChange = {},
        onCorreoChange = {},
        onContrasenhaChange = {},
        onRepitaContrasenhaChange = {},
        onRegisterClicked = {},
        onNavigateToLogin = {}
    )
}
