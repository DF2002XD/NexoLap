package com.example.nexolap.viewmodel.vm

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexolap.Data.repository.UsuarioRepo
import com.example.nexolap.modelo.UsuarioDTO
import com.example.nexolap.viewmodel.uistate.RegistroUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistroPageVM : ViewModel() {
    private val _uiState = MutableStateFlow(RegistroUIState())
    val uiState: StateFlow<RegistroUIState> = _uiState.asStateFlow()

    private val coloresFondo = listOf(
        Color(0xFFEF5350), Color(0xFFEC407A), Color(0xFFAB47BC),
        Color(0xFF7E57C2), Color(0xFF5C6BC0), Color(0xFF42A5F5),
        Color(0xFF29B6F6), Color(0xFF26C6DA), Color(0xFF26A69A),
        Color(0xFF66BB6A), Color(0xFF9CCC65), Color(0xFFD4E157),
        Color(0xFFFFEE58), Color(0xFFFFCA28), Color(0xFFFFA726),
        Color(0xFFFF7043)
    )

    fun onNombreChange(nombre: String) {
        _uiState.update { it.copy(usuario = it.usuario.copy(UsuarioNombre = nombre)) }
    }

    fun onCorreoChange(correo: String) {
        _uiState.update { it.copy(usuario = it.usuario.copy(UsuarioCorreo = correo)) }
    }

    fun onContrasenhaChange(contra: String) {
        _uiState.update { it.copy(usuario = it.usuario.copy(UsuarioContrasenha = contra)) }
    }

    fun onRepitaContrasenhaChange(contra: String) {
        _uiState.update { it.copy(repitaContrasenha = contra) }
    }

    fun registrarUsuario(onSuccess: () -> Unit) {
        val usuario = _uiState.value.usuario
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val nextId = (UsuarioRepo.usuario.maxOfOrNull { it.id } ?: 0) + 1

                val colorAleatorio = coloresFondo.random().toArgb()
                
                val dto = UsuarioDTO(
                    id = nextId,
                    nombre = usuario.UsuarioNombre,
                    correo = usuario.UsuarioCorreo,
                    contraseña = usuario.UsuarioContrasenha,
                    color = colorAleatorio // Guardamos el color aquí
                )

                UsuarioRepo.usuario.add(dto)
                _uiState.update { it.copy(isLoading = false, registrationSuccess = true) }
                onSuccess()
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al registrar el usuario: ${e.message}") }
            }
        }
    }

    fun resetState() {
        _uiState.value = RegistroUIState()
    }
}
