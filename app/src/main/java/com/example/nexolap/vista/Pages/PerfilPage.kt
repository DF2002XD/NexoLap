package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexolap.viewmodel.vm.PerfilPageVM
import com.example.nexolap.vista.myComponents.Perfil


@Composable
fun PerfilPage(
    userId: Int,
    modifier: Modifier = Modifier,
    onAccountDeleted: () -> Unit = {},
    vm: PerfilPageVM = viewModel()
) {
    val uiState by vm.uiState.collectAsState()
    val colorPerfil by vm.colorPerfil.collectAsState()

    LaunchedEffect(userId) {
        vm.obtenerUsuario(userId)
    }

    val usuarioActual = uiState.listaUsuarios.firstOrNull()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (usuarioActual != null) {
            Perfil(
                nombreUsuarioInicial = usuarioActual.nombre,
                colorFondo = colorPerfil,
                onActualizarNombre = { vm.actualizarNombre(usuarioActual.id, it) },
                onActualizarCorreo = { vm.actualizarCorreo(usuarioActual.id, it) },
                onActualizarContrasenha = { vm.actualizarContrasenha(usuarioActual.id, it) },
                onActualizarColor = { vm.obtenerUsuario(usuarioActual.id) },
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
}

@Preview
@Composable
fun PerfilPagePreview() {
    PerfilPage(userId = 1)
}
