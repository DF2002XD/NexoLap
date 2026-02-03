package com.example.nexolap.vista.myComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexolap.R



/**
 * Un Composable que muestra la pantalla de perfil de usuario.
 *
 * Permite al usuario ver y editar su información de perfil, incluyendo la foto de perfil,
 * el nombre de usuario, el correo electrónico y la contraseña. La pantalla es desplazable
 * para adaptarse a diferentes tamaños de pantalla y valida que las contraseñas nuevas coincidan.
 *
 * @param nombreUsuarioInicial El nombre de usuario inicial que se mostrará en el perfil.
 * @param correoInicial El correo electrónico inicial que se mostrará y se podrá editar.
 */
@Composable
fun Perfil(
    nombreUsuarioInicial: String = "Nombre Usuario",
    correoInicial: String = "hello@reallygreatsite.com"
){
    var nombreUsuario by remember { mutableStateOf(nombreUsuarioInicial) }
    var correo by remember { mutableStateOf(correoInicial) }
    var nuevaContrasenha by remember { mutableStateOf("") }
    var repetirContrasenha by remember { mutableStateOf("") }
    val contrasenhasCoinciden = nuevaContrasenha == repetirContrasenha

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Permite hacer scroll si el contenido no cabe
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier.padding(bottom = 16.dp)) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu imagen
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )

            IconButton(
                onClick = { /* TODO: Lógica para cambiar la foto de perfil */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 10.dp, y = 10.dp) // Ajusta el offset para que no esté pegado
                    .background(
                        MaterialTheme.colorScheme.surface,
                        CircleShape
                    )
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Editar foto de perfil")
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = nombreUsuario, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = { /* TODO: Lógica para editar el nombre de usuario (ej. abrir un dialogo) */ }) {
                Icon(Icons.Default.Edit, contentDescription = "Editar nombre de usuario")
            }
        }

        Text("Correo electrónico", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = correo,
            onValueChange = { correo = it },
            modifier = Modifier.fillMaxWidth(),
            readOnly = false // O puedes ponerlo en true y habilitarlo al pulsar un botón
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* TODO: Lógica para actualizar el correo */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar correo")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text("Contraseña", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        CampoContrasenha(
            valor = nuevaContrasenha,
            onValorCambiado = { nuevaContrasenha = it },
            label = "Nueva contraseña"

        )
        Spacer(modifier = Modifier.height(16.dp))

        CampoContrasenha(
            valor = repetirContrasenha,
            onValorCambiado = { repetirContrasenha = it },
            label = "Repetir contraseña",
            esError = !contrasenhasCoinciden && repetirContrasenha.isNotEmpty()
        )

        if (!contrasenhasCoinciden && repetirContrasenha.isNotEmpty()) {
            Text(
                text = "Las contraseñas no coinciden",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp),
                textAlign = TextAlign.Start,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Boton("Actualizar contraseña", onClick = { /* TODO: Lógica para actualizar la contraseña */ },
            enabled = contrasenhasCoinciden && nuevaContrasenha.isNotEmpty())
    }
}

    @Preview
    @Composable
    fun PerfilPreview() {
        Perfil()
    }