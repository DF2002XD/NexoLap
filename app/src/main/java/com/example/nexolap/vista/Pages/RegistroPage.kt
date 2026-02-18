package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexolap.viewmodel.vm.RegistroPageVM
import com.example.nexolap.vista.myComponents.Registro


@Composable
fun RegistroPage(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit = {},
    vm: RegistroPageVM = viewModel()
) {
    val uiState by vm.uiState.collectAsState()


    Scaffold(modifier = modifier) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Registro(
                    usuario = uiState.usuario,
                    repitaContrasenha = uiState.repitaContrasenha,
                    onNombreChange = vm::onNombreChange,
                    onCorreoChange = vm::onCorreoChange,
                    onContrasenhaChange = vm::onContrasenhaChange,
                    onRepitaContrasenhaChange = vm::onRepitaContrasenhaChange,
                    onRegisterClicked = {
                        vm.registrarUsuario {
                            onNavigateToLogin()
                            vm.resetState()
                        }
                    },
                    onNavigateToLogin = onNavigateToLogin
                )
            }

            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            uiState.error?.let { errorMsg ->
                Text(
                    text = errorMsg,
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun RegistroPagePreview() {
    RegistroPage()
}
