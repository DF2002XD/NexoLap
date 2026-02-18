package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexolap.viewmodel.vm.LoginPageVM
import com.example.nexolap.vista.myComponents.LoginSesion


@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    onLoginSuccess: (Int) -> Unit = {},
    onNavigateToRegister: () -> Unit = {},
    vm: LoginPageVM = viewModel()
) {
    val uiState by vm.uiState.collectAsState()

    Scaffold(modifier = modifier) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginSesion(
                    keepLogged = uiState.keepLogged,
                    onKeepLoggedChange = { vm.onKeepLoggedChange(it) },
                    errorMessage = uiState.errorMessage,
                    onLoginClicked = { correo, contra ->
                        vm.login(
                            correo = correo,
                            contrasenha = contra,
                            onSuccess = { usuario -> 
                                onLoginSuccess(usuario.id)
                            }
                        )
                    },
                    onNavigateToRegister = onNavigateToRegister
                )
            }

            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage()
}
