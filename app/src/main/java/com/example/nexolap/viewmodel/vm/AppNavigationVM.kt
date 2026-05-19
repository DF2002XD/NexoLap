package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexolap.data.retrofit.repo.IUsuarioApiRepo
import com.example.nexolap.data.retrofit.repo.UsuarioApiRepo
import com.example.nexolap.viewmodel.uistate.AppNavigationUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel responsable de gestionar el estado de la navegación principal de la aplicación.
 *
 * Se encarga de la lógica para determinar el punto de entrada inicial de la aplicación
 * basándose en el estado de la sesión del usuario (por ejemplo, redirigir a la pantalla de
 * inicio de sesión o al panel principal) y gestiona las operaciones de cierre de sesión.
 *
 * @property uiState Un [StateFlow] que emite [AppNavigationUIState], representando el estado
 * actual de la navegación y la verificación de la sesión.
 */
class AppNavigationVM(

) : ViewModel() {

    private val _uiState = MutableStateFlow(AppNavigationUIState(isCheckUserSession = true))
    val uiState: StateFlow<AppNavigationUIState> = _uiState.asStateFlow()

    private val userRepo: IUsuarioApiRepo =
       UsuarioApiRepo.getInstance()

    fun checkUserSession() {
        viewModelScope.launch {
            userRepo.checkStoredSession(
                onSuccess = { user ->
                    _uiState.update {
                        it.copy(
                            isCheckUserSession = false,
                            isUserLoggedIn = true,
                            initialRoute = "principal/${user.id}",
                            userId = user.id,
                            userName = user.name,
                            userColor = user.color
                        )
                    }
                },
                onError = {
                    _uiState.update {
                        it.copy(
                            isCheckUserSession = false,
                            isUserLoggedIn = false,
                            initialRoute = "login"
                        )
                    }
                }
            )
        }
    }

    fun logout(onSuccess: () -> Unit) {
        userRepo.logoutUser(
            onSuccess = {
                _uiState.update { it.copy(isUserLoggedIn = false, userId = null, userName = null, userColor = null) }
                onSuccess()
            },
            onError = {}
        )
    }

    fun updateUserInfo() {
        val user = userRepo.getCurrentUser()
        if (user != null) {
            _uiState.update {
                it.copy(
                    userId = user.id,
                    userName = user.name,
                    userColor = user.color
                )
            }
        }
    }
}
