package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexolap.viewmodel.vm.PerfilPageVM
import com.example.nexolap.vista.myComponents.Perfil


/**
 * Composable function that represents the profile page of a user.
 *
 * This page fetches and displays user information based on the provided [userId].
 * It allows the user to update their name, email, password, and profile color,
 * as well as delete their account.
 *
 * @param userId The unique identifier of the user to be displayed.
 * @param modifier The [Modifier] to be applied to the layout.
 * @param onAccountDeleted Callback function invoked when the user's account is successfully deleted.
 * @param onUserUpdated Callback function invoked when user information (name, color, etc.) is updated.
 * @param vm The [PerfilPageVM] instance that manages the state and logic for this page.
 */
@Composable
fun PerfilPage(
    userId: String,
    modifier: Modifier = Modifier,
    onAccountDeleted: () -> Unit = {},
    onUserUpdated: () -> Unit = {},
    vm: PerfilPageVM = viewModel()
) {
    val uiState by vm.uiState.collectAsState()
    val colorPerfil by vm.colorPerfil.collectAsState()

    LaunchedEffect(userId) {
        vm.obtenerUsuario(userId)
    }

    val usuarioActual = uiState.listaUsuarios.firstOrNull()

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (usuarioActual != null) {
                Perfil(
                    nombreUsuarioInicial = usuarioActual.nombre,
                    correoActual = usuarioActual.correo,
                    colorFondo = colorPerfil,
                    onActualizarNombre = {
                        vm.actualizarNombre(usuarioActual.id, it)
                        onUserUpdated()
                    },
                    onActualizarCorreo = { 
                        vm.actualizarCorreo(usuarioActual.id, it)
                        onUserUpdated()
                    },
                    onActualizarContrasenha = { pass, repeat -> 
                        vm.actualizarContrasenha(usuarioActual.id, pass, repeat) 
                        onUserUpdated()
                    },
                    onActualizarColor = { 
                        vm.obtenerUsuario(usuarioActual.id)
                        onUserUpdated()
                    },
                    onEliminarCuenta = {
                        vm.eliminarUsuario(usuarioActual.id) {
                            onAccountDeleted()
                        }
                    }
                )
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }

        uiState.error?.let { errorMsg ->
            Text(
                text = errorMsg,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun PerfilPagePreview() {
    PerfilPage(userId = "1")
}
