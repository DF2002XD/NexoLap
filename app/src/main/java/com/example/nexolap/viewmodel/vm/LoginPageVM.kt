package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexolap.data.retrofit.repo.IUsuarioApiRepo
import com.example.nexolap.data.retrofit.repo.UsuarioApiRepo
import com.example.nexolap.modelo.UsuarioDTO
import com.example.nexolap.viewmodel.uistate.LoginPageUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la lógica de negocio y el estado de la interfaz de usuario
 * para la pantalla de inicio de sesión.
 */
class LoginPageVM : ViewModel() {

    private val _uiState = MutableStateFlow(LoginPageUIState())
    val uiState: StateFlow<LoginPageUIState> = _uiState.asStateFlow()

    private val repo: IUsuarioApiRepo = UsuarioApiRepo.getInstance()

    fun onKeepLoggedChange(keepLogged: Boolean) {
        _uiState.update { it.copy(keepLogged = keepLogged) }
    }

    fun login(correo: String, contrasenha: String, onSuccess: (UsuarioDTO) -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            repo.loginUser(
                correo = correo,
                contrasenha = contrasenha,
                keepLogged = _uiState.value.keepLogged,
                onSuccess = { usuario ->
                    _uiState.update { it.copy(isLoading = false, isLoginSuccessful = true) }
                    onSuccess(usuario)
                },
                onError = {
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = "Contraseña incorrecta")
                    }
                }
            )
        }
    }
}

