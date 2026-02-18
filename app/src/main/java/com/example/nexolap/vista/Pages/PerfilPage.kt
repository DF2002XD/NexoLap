package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexolap.viewmodel.vm.PerfilPageVM
import com.example.nexolap.vista.myComponents.ButtomAppBarNav
import com.example.nexolap.vista.myComponents.Perfil

/**
 * Un Composable que representa la pantalla del perfil de usuario.
 *
 * @param userId El ID del usuario que se va a mostrar.
 * @param modifier El modificador que se aplicará al diseño.
 * @param vm El ViewModel que mantiene el estado del perfil.
 */
@Composable
fun PerfilPage(
    userId: Int,
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    vm: PerfilPageVM = viewModel()
) {
    val uiState by vm.uiState.collectAsState()

    // Cargamos el usuario cuando cambia el userId
    LaunchedEffect(userId) {
        vm.obtenerUsuario(userId)
    }

    val usuarioActual = uiState.listaUsuarios.firstOrNull()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            ButtomAppBarNav(
                onHomeClick = onHomeClick,
                onSearchClick = onSearchClick,
                onProfileClick = onProfileClick,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (usuarioActual != null) {
                Perfil(
                    nombreUsuarioInicial = usuarioActual.nombre,
                    colorFondo = vm.colorPerfil,
                    onActualizarNombre = { vm.actualizarNombre(usuarioActual.id, it) },
                    onActualizarCorreo = { vm.actualizarCorreo(usuarioActual.id, it) },
                    onActualizarContrasenha = { vm.actualizarContrasenha(usuarioActual.id, it) },
                    onEliminarCuenta = { vm.eliminarUsuario(usuarioActual.id) { /* Navegar fuera */ } }
                )
            } else {
                Text(text = "Cargando perfil...", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun PerfilPagePreview() {
    PerfilPage(userId = 1)
}
