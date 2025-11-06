package com.example.nexolap.myComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InicioSesion (correo : String = "", contrasenha : String = ""){
    var correoValue by remember { mutableStateOf(correo) }
    var contrasenhaValue by remember { mutableStateOf(contrasenha) }
    var contrasenhaVisible by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Inicio de Sesión", textAlign = TextAlign.Center)

        Column(modifier = Modifier.padding(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Correo", textAlign = TextAlign.Center)
            TextField(value = correoValue, onValueChange = {correoValue = it}, suffix = { Text(text = "@gmail.com") })
            
        }
        Column(modifier = Modifier.padding(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Contraseña", textAlign = TextAlign.Center)
            TextField(
                value = contrasenhaValue,
                onValueChange = { contrasenhaValue = it },
                visualTransformation = if (contrasenhaVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (contrasenhaVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    
                    // Localized description for accessibility services
                    val description = if (contrasenhaVisible) "Hide password" else "Show password"
                    
                    // Toggle button to hide or show password
                    IconButton(onClick = {contrasenhaVisible = !contrasenhaVisible}){
                        Icon(imageVector  = image, description)
                    }
                }
            )
        }
        Boton(nombre = "Iniciar Sesión", {})




    }
}

@Composable
fun Registro(){

}

@Preview
@Composable
fun PreviewAcceso(){
    InicioSesion()
}

@Preview
@Composable
fun PreviewRegistro(){
    Registro()
}
