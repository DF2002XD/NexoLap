@file:Suppress("DEPRECATION")

package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexolap.R

/**
 * Un Composable que muestra una pantalla de inicio de sesión de usuario.
 *
 * Esta pantalla incluye campos de entrada para un correo electrónico y una contraseña,
 * un botón para enviar las credenciales y un botón para navegar a la pantalla de registro.
 * El campo de la contraseña tiene un interruptor para mostrar u ocultar el texto de la contraseña.
 *
 * @param correo El valor inicial para el campo de entrada del correo electrónico. Por defecto es una cadena vacía.
 * @param contrasenha El valor inicial para el campo de entrada de la contraseña. Por defecto es una cadena vacía.
 */
@Composable
fun InicioSesion (correo : String = "", contrasenha : String = ""){
    var correoValue by remember { mutableStateOf(correo) }
    var contrasenhaValue by remember { mutableStateOf(contrasenha) }
    var contrasenhaVisible by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Column(modifier = Modifier.padding(top = 120.dp, bottom = 100.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Inicio de Sesión", fontSize = 30.sp, textAlign = TextAlign.Center)
        }
        Column(modifier = Modifier.padding().fillMaxHeight(0.2f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Correo", modifier = Modifier.padding(bottom = 10.dp), textAlign = TextAlign.Center, fontSize = 20.sp)
            TextField(value = correoValue, onValueChange = {correoValue = it}, suffix = { Text(text = "@gmail.com") })
            
        }
        Column(modifier = Modifier.padding().fillMaxHeight(0.4f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Contraseña", modifier = Modifier.padding(bottom = 10.dp), textAlign = TextAlign.Center, fontSize = 20.sp)
            TextField(
                value = contrasenhaValue,
                onValueChange = { contrasenhaValue = it },
                visualTransformation = if (contrasenhaVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (contrasenhaVisible)
                        painterResource(id = R.drawable.outline_visibility_24)
                    else painterResource(id = R.drawable.outline_visibility_off_24)
                    
                    // Localized description for accessibility services
                    val description = if (contrasenhaVisible) "Hide password" else "Show password"
                    
                    // Toggle button to hide or show password
                    IconButton(onClick = {contrasenhaVisible = !contrasenhaVisible}){
                        Icon(painter  = image, contentDescription = description)
                    }
                }
            )
        }
        Column(modifier = Modifier.padding().fillMaxHeight(0.3f), horizontalAlignment = Alignment.CenterHorizontally) {
            Boton(nombre = "Iniciar Sesión"){
                // Lógica para iniciar sesión
                // Puedes implementar aquí la validación de credenciales
                // y la navegación a la siguiente pantalla si es exitosa
            }
        }
        Column(modifier = Modifier.padding(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            Boton(nombre = "Registrarse"){
                // Lógica para navegar a la pantalla de registro
            }
        }
    }
}





/**
 * Un Composable que muestra una pantalla de registro de usuario.
 *
 * Esta pantalla permite a un nuevo usuario crear una cuenta proporcionando
 * su nombre, correo electrónico y una contraseña. Incluye validación para asegurar
 * que las contraseñas introducidas coincidan. También proporciona un enlace para
 * navegar a la pantalla de inicio de sesión si el usuario ya tiene una cuenta.
 *
 * @param nombre El valor inicial para el campo de entrada del nombre. Por defecto es una cadena vacía.
 * @param correo El valor inicial para el campo de entrada del correo electrónico. Por defecto es una cadena vacía.
 * @param contrasenha El valor inicial para el campo de entrada de la contraseña. Por defecto es una cadena vacía.
 * @param repitaContrasenha El valor inicial para el campo de confirmación de la contraseña. Por defecto es una cadena vacía.
 * @param onClick Una función lambda que se invoca cuando el usuario hace clic en el enlace "Inicia sesión aquí",
 *                típicamente para navegar a la pantalla de inicio de sesión.
 */
@Composable
fun Registro(nombre : String = "", correo : String = "", contrasenha : String = "", repitaContrasenha : String = "", onClick : () -> Unit){
    var nombreValue by remember { mutableStateOf(nombre) }
    var correoValue by remember { mutableStateOf(correo) }
    var contrasenhaValue by remember { mutableStateOf(contrasenha) }
    var repitaContrasenhaValue by remember { mutableStateOf(repitaContrasenha) }
    var contrasenhaVisible by remember { mutableStateOf(false) }
    var repitaContrasenhaVisible by remember { mutableStateOf(false) }
    val contrasenhasCoinciden = contrasenhaValue == repitaContrasenhaValue
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // 1. Título de la pantalla
        Column(
            modifier = Modifier.padding(top = 80.dp, bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Crea nueva cuenta", fontSize = 30.sp, textAlign = TextAlign.Center)
            // Texto clickeable para ir a la pantalla de inicio de sesión
            val annotatedText = buildAnnotatedString {
                append("¿Ya estás registrado? Inicia sesión ")
                // Empieza a "anotar" o marcar la parte que será un enlace
                withLink(
                    LinkAnnotation.Clickable(
                        tag = "LOGIN", // Un identificador único para este enlace
                        linkInteractionListener = {
                            // Navega a la pantalla de inicio de sesión
                            onClick()
                        }
                    )
                ) {
                    withStyle(style = SpanStyle(color = Color.Blue)){
                        // Este es el texto que se verá como un enlace
                        append("aquí")
                    }
                }
            }
            // Ahora usa un Text Composable normal, que sabe cómo manejar los enlaces
            Text(
                text = annotatedText,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // 2. Campo de texto para el Nombre
        Column(
            modifier = Modifier.padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "NOMBRE", modifier = Modifier.padding(bottom = 10.dp), fontSize = 16.sp)
            TextField(value = nombreValue, onValueChange = { nombreValue = it })
        }

        // 3. Campo de texto para el Correo
        Column(
            modifier = Modifier.padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "CORREO", modifier = Modifier.padding(bottom = 10.dp), fontSize = 16.sp)
            TextField(value = correoValue, onValueChange = { correoValue = it }, suffix = { Text(text = "@gmail.com") })
        }

        // 4. Campo de texto para la Contraseña
        Column(
            modifier = Modifier.padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "CONTRASEÑA", modifier = Modifier.padding(bottom = 10.dp), fontSize = 16.sp)
            TextField(
                value = contrasenhaValue,
                onValueChange = { contrasenhaValue = it },
                visualTransformation = if (contrasenhaVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (contrasenhaVisible) painterResource(R.drawable.outline_visibility_24) else painterResource(R.drawable.outline_visibility_off_24)
                    IconButton(onClick = { contrasenhaVisible = !contrasenhaVisible }) {
                        Icon(painter = image, contentDescription = "Toggle password visibility")
                    }
                }
            )
        }

        // 5. Campo de texto para Repetir Contraseña
        Column(
            modifier = Modifier.padding(bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "REPITE CONTRASEÑA", modifier = Modifier.padding(bottom = 10.dp), fontSize = 16.sp)
            TextField(
                value = repitaContrasenhaValue,
                onValueChange = { repitaContrasenhaValue = it },
                visualTransformation = if (repitaContrasenhaVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = !contrasenhasCoinciden, // Muestra el campo en rojo si no coinciden
                trailingIcon = {
                    val image = if (repitaContrasenhaVisible) painterResource(R.drawable.outline_visibility_24) else painterResource(R.drawable.outline_visibility_off_24)
                    IconButton(onClick = { repitaContrasenhaVisible = !repitaContrasenhaVisible }) {
                        Icon(painter = image, contentDescription = "Toggle password visibility")
                    }
                }
            )
            if (!contrasenhasCoinciden && repitaContrasenhaValue.isNotEmpty()) {
                Text(
                    text = "Las contraseñas no coinciden",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
        Column(
            modifier = Modifier.padding().fillMaxHeight(0.3f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            // 6. Botón de Registro
            Boton(nombre = "Crear cuenta") {
                // Lógica para registrar al usuario
                // Puedes comprobar aquí de nuevo si las contraseñas coinciden antes de proceder
            }
        }
    }
}

@Preview
@Composable
fun PreviewAcceso(){
    InicioSesion()
}

@Preview
@Composable
fun PreviewRegistro(){
    Registro(onClick = {})
}
