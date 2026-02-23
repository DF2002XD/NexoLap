package com.example.nexolap.viewmodel.vm

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexolap.Data.retrofit.repo.IUsuarioApiRepo
import com.example.nexolap.Data.retrofit.repo.UsuarioApiRepo
import com.example.nexolap.modelo.UsuarioDTORetroFit
import com.example.nexolap.viewmodel.uistate.RegistroUIStateApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la lógica de negocio y el estado de la pantalla de registro de usuarios.
 *
 * Esta clase se comunica con el repositorio [IUsuarioApiRepo] para procesar el alta de nuevos usuarios,
 * gestiona las actualizaciones de los campos del formulario (nombre, correo, contraseñas) y asigna
 * de forma aleatoria un color de perfil de una lista predefinida.
 *
 * @property uiState Flujo de estado ([StateFlow]) que expone el estado actual de la interfaz [RegistroUIStateApi].
 * @property repo Instancia del repositorio utilizada para realizar las operaciones de persistencia de usuarios.
 * @property coloresFondo Lista de colores disponibles para ser asignados aleatoriamente al perfil del usuario.
 */
class RegistroPageVM : ViewModel() {
    private val _uiState = MutableStateFlow(RegistroUIStateApi())
    val uiState: StateFlow<RegistroUIStateApi> = _uiState.asStateFlow()

    private val repo: IUsuarioApiRepo = UsuarioApiRepo.getInstance()

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
        val usuarioState = _uiState.value.usuario
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val colorAleatorio = coloresFondo.random().toArgb()
            val dto = UsuarioDTORetroFit(
                id = "",
                name = usuarioState.UsuarioNombre,
                email = usuarioState.UsuarioCorreo,
                passwd = usuarioState.UsuarioContrasenha,
                color = colorAleatorio
            )

            repo.create(
                dto,
                onSucess = {
                    _uiState.update { it.copy(isLoading = false, registrationSuccess = true) }
                    onSuccess()
                },
                onError = {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Error al registrar el usuario"
                        )
                    }
                }
            )
        }
    }

    fun resetState() {
        _uiState.value = RegistroUIStateApi()
    }
}
