package com.example.nexolap.vista.myComponents

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.nexolap.R
import java.io.InputStream

/**
 * Pantalla de perfil de usuario que permite visualizar y editar la información personal.
 *
 * Permite al usuario cambiar su nombre, correo electrónico, contraseña y el color
 * de fondo de su avatar.
 *
 * @param nombreUsuarioInicial El nombre de usuario actual para mostrar inicialmente.
 * @param colorFondo El color de fondo del avatar circular.
 * @param onActualizarNombre Callback que se ejecuta al guardar un nuevo nombre de usuario.
 * @param onActualizarCorreo Callback que se ejecuta al solicitar la actualización del correo.
 * @param onActualizarContrasenha Callback que se ejecuta al actualizar la contraseña.
 * @param onActualizarColor Callback que se ejecuta al solicitar un cambio en el color del perfil.
 * @param onEliminarCuenta Callback que se ejecuta al solicitar la eliminación definitiva de la cuenta.
 */
@Composable
fun Perfil(
    nombreUsuarioInicial: String,
    correoActual: String = "",
    colorFondo: Color = Color.Gray,
    fotoActualBase64: String? = null,
    onActualizarNombre: (String) -> Unit = {},
    onActualizarCorreo: (String) -> Unit = {},
    onActualizarContrasenha: (String, String) -> Unit = { _, _ -> },
    onActualizarColor: () -> Unit = {},
    onActualizarImagen: (Bitmap) -> Unit = {},
    onEliminarCuenta: () -> Unit = {}
) {
    val context = LocalContext.current
    var nombreUsuario by remember { mutableStateOf(nombreUsuarioInicial) }
    var nuevoCorreo by remember { mutableStateOf(correoActual) }
    var nuevaContrasenha by remember { mutableStateOf("") }
    var repetirContrasenha by remember { mutableStateOf("") }
    var isEditingNombre by remember { mutableStateOf(false) }
    var showImageSourceDialog by remember { mutableStateOf(false) }
    val contrasenhasCoinciden = nuevaContrasenha == repetirContrasenha

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val inputStream: InputStream? = context.contentResolver.openInputStream(it)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            bitmap?.let { b -> onActualizarImagen(b) }
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        bitmap?.let { onActualizarImagen(it) }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch()
        }
    }

    // Sincronizamos el nombre y el correo con los valores del ViewModel
    LaunchedEffect(nombreUsuarioInicial, correoActual) {
        nombreUsuario = nombreUsuarioInicial
        nuevoCorreo = correoActual
    }

    val inicial = if (nombreUsuario.isNotEmpty()) nombreUsuario.take(1).uppercase() else "?"

    val fotoBitmap = remember(fotoActualBase64) {
        if (!fotoActualBase64.isNullOrBlank()) {
            try {
                val decodedString = Base64.decode(fotoActualBase64, Base64.DEFAULT)
                BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }

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
                if (fotoBitmap != null) {
                    Image(
                        bitmap = fotoBitmap.asImageBitmap(),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = inicial,
                        color = Color.White,
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            IconButton(
                onClick = { showImageSourceDialog = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 10.dp, y = 10.dp)
                    .background(
                        MaterialTheme.colorScheme.surface,
                        CircleShape
                    )
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Cambiar foto de perfil")
            }
        }

        if (showImageSourceDialog) {
            AlertDialog(
                onDismissRequest = { showImageSourceDialog = false },
                title = { Text(text = "Cambiar foto de perfil") },
                text = { Text(text = "Selecciona una opción") },
                confirmButton = {
                    TextButton(onClick = {
                        galleryLauncher.launch("image/*")
                        showImageSourceDialog = false
                    }) {
                        Text("Galería")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        val permissionCheckResult = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                            cameraLauncher.launch()
                        } else {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                        showImageSourceDialog = false
                    }) {
                        Text("Cámara")
                    }
                }
            )
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
                onActualizarContrasenha(nuevaContrasenha, repetirContrasenha)
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
    Perfil(
        nombreUsuarioInicial = "Usuario de Prueba",
        correoActual = "prueba@gmail.com"
    )
}
