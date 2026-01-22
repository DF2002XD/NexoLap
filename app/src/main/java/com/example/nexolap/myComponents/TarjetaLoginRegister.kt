package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexolap.Data.UsuarioData
import com.example.nexolap.R



/**
 * Un Composable que muestra la pantalla de inicio de sesión.
 *
 * Esta pantalla contiene campos de entrada para el correo electrónico y la contraseña del usuario.
 * Proporciona un botón para intentar iniciar sesión y otro para navegar a la pantalla de registro
 * si el usuario no tiene una cuenta.
 *
 * @param onLoginClicked Una función lambda que se invoca cuando el usuario hace clic en el botón "Iniciar Sesión".
 *                       Recibe el correo y la contraseña introducidos como parámetros.
 * @param onNavigateToRegister Una función lambda que se invoca cuando el usuario hace clic en el botón "Registrarse",
 *                             para navegar a la pantalla de registro.
 */
@Composable
fun InicioSesion (
    onLoginClicked : (correo: String, contrasenha: String) -> Unit,
    onNavigateToRegister : () -> Unit
){
    var correo by remember { mutableStateOf("") }
    var contrasenha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(150.dp))

        Text(text = stringResource(R.string.inicio_de_sesion), fontSize = 40.sp, modifier = Modifier.padding(bottom = 60.dp))


        Spacer(modifier = Modifier.height(100.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Alinea el texto a la izquierda
        ) {
            Text(text = stringResource(R.string.correo), fontSize = 25.sp,)
            TextField(
                value = correo,
                onValueChange = { correo = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                suffix = { Text(text = "@gmail.com") }
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CampoContrasenha(
                label = stringResource(R.string.contrasenha),
                valor = contrasenha,
                onValorCambiado = { contrasenha = it }
            )
        }

        Spacer(modifier = Modifier.height(70.dp))

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
    }
}

/**
 * Un Composable que muestra la pantalla de registro de usuario.
 *
 * Incluye campos de entrada para el nombre de usuario, correo electrónico y contraseña, junto con la confirmación de la contraseña.
 * Proporciona un botón para crear la cuenta y un enlace para navegar a la pantalla de inicio de sesión si el usuario
 * ya está registrado.
 *
 * @param onRegisterClicked Una función lambda que se invoca cuando se hace clic en el botón "Crear cuenta".
 *                          Pasa los datos del usuario introducidos (`UsuarioData`) como parámetro.
 * @param onNavigateToLogin Una función lambda que se invoca cuando el usuario hace clic en el enlace "aquí"
 *                          para navegar a la pantalla de inicio de sesión.
 */
@Composable
fun Registro(
    onRegisterClicked: (usuario: UsuarioData) -> Unit,
    onNavigateToLogin: () -> Unit
){
    var datosUsuario by remember {
        mutableStateOf(UsuarioData("", "", ""))
    }
    var repitaContrasenha by remember { mutableStateOf("") }
    val contrasenhasCoinciden = datosUsuario.UsuarioContrasenha == repitaContrasenha

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(top = 80.dp, bottom = 40.dp), // Aumentado espacio inferior
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.crear_nueva_cuenta), fontSize = 40.sp, textAlign = TextAlign.Center)
            val annotatedText = buildAnnotatedString {
                append(stringResource(R.string.ya_esta_registrado))
                append(" ")
                withLink(
                    LinkAnnotation.Clickable(
                        tag = "LOGIN",
                        linkInteractionListener = { onNavigateToLogin() }
                    )
                ) {
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append(stringResource(R.string.login_aqui))
                    }
                }
            }
            Text(text = annotatedText, modifier = Modifier.padding(top = 10.dp), fontSize = 16.sp)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.nombre), modifier = Modifier.padding(bottom = 10.dp), fontSize = 25.sp)
            TextField(
                value = datosUsuario.UsuarioNombre,
                onValueChange = { nuevoNombre ->
                    datosUsuario = datosUsuario.copy(UsuarioNombre = nuevoNombre)
                },
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
            Text(text = stringResource(R.string.correo), modifier = Modifier.padding(bottom = 10.dp), fontSize = 25.sp)
            TextField(
                value = datosUsuario.UsuarioCorreo,
                onValueChange = { nuevoCorreo ->
                    datosUsuario = datosUsuario.copy(UsuarioCorreo = nuevoCorreo)
                },
                modifier = Modifier.fillMaxWidth(),
                suffix = { Text(text = "@gmail.com") },
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
                valor = datosUsuario.UsuarioContrasenha,
                onValorCambiado = { nuevaContra ->
                    datosUsuario = datosUsuario.copy(UsuarioContrasenha = nuevaContra)
                },
                label = stringResource(R.string.contrasenha)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CampoContrasenha(
                valor = repitaContrasenha,
                onValorCambiado = { repitaContrasenha = it },
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
            onClick = { onRegisterClicked(datosUsuario) },
            enabled = contrasenhasCoinciden && datosUsuario.UsuarioNombre.isNotBlank() // y otros campos
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
fun PreviewAcceso(){
    InicioSesion(onLoginClicked = {_,_ ->}, onNavigateToRegister = {})
}

@Preview
@Composable
fun PreviewRegistro(){
    Registro(onRegisterClicked = {UsuarioData("","","")}, onNavigateToLogin = {})
}
