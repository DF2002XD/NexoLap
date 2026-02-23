package com.example.nexolap.vista.Pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexolap.viewmodel.vm.LoginPageVM
import com.example.nexolap.vista.myComponents.LoginSesion


/**
 * Composable function that represents the login screen of the application.
 *
 * It manages the user interface for authenticating users, showing a loading indicator
 * during the process and handling error messages.
 *
 * @param modifier The [Modifier] to be applied to the layout.
 * @param onLoginSuccess Callback function triggered when authentication is successful,
 * providing the user's ID as a [String].
 * @param onNavigateToRegister Callback function triggered when the user requests to
 * navigate to the registration screen.
 * @param vm The [LoginPageVM] that handles the business logic and state for this screen.
 */
@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    onLoginSuccess: (String) -> Unit = {},
    onNavigateToRegister: () -> Unit = {},
    vm: LoginPageVM = viewModel()
) {
    val uiState by vm.uiState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
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

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage()
}
