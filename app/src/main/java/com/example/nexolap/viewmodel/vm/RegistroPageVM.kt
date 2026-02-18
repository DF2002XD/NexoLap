package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexolap.Data.Usuario
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
                val dto = UsuarioDTO(
                    id = nextId,
                    nombre = usuario.UsuarioNombre,
                    correo = usuario.UsuarioCorreo,
                    contraseña = usuario.UsuarioContrasenha
                )
                // Simulamos la creación del usuario añadiéndolo a la lista estática del repo
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
