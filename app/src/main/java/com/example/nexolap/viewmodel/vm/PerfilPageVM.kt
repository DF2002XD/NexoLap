package com.example.nexolap.viewmodel.vm

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.example.nexolap.Data.retrofit.repo.IUsuarioApiRepo
import com.example.nexolap.Data.retrofit.repo.UsuarioApiRepo
import com.example.nexolap.modelo.UsuarioDTORetroFit
import com.example.nexolap.viewmodel.uistate.ListaUsuarioUIState
import com.example.nexolap.viewmodel.uistate.UsuarioUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel encargado de gestionar la lógica de negocio y el estado de la interfaz de usuario
 * para la pantalla de perfil del usuario.
 *
 * Proporciona funcionalidades para obtener los datos de un usuario desde un repositorio (local o remoto),
 * actualizar la información personal (nombre, correo, contraseña), gestionar el color de fondo
 * personalizado del perfil y eliminar la cuenta del usuario.
 *
 * @property uiState Flujo de estado que contiene la información del usuario actual para ser mostrada en la UI.
 * @property colorPerfil Flujo de estado que representa el color asignado al perfil del usuario.
 */
class PerfilPageVM : ViewModel() {

    private val _uiState = MutableStateFlow(ListaUsuarioUIState())
    val uiState: StateFlow<ListaUsuarioUIState> = _uiState.asStateFlow()

    private val repo: IUsuarioApiRepo = UsuarioApiRepo.getInstance()


    private val _colorPerfil = MutableStateFlow(Color.Gray)
    val colorPerfil: StateFlow<Color> = _colorPerfil.asStateFlow()

    private val coloresFondo = listOf(
        Color(0xFFEF5350), Color(0xFFEC407A), Color(0xFFAB47BC),
        Color(0xFF7E57C2), Color(0xFF5C6BC0), Color(0xFF42A5F5),
        Color(0xFF29B6F6), Color(0xFF26C6DA), Color(0xFF26A69A),
        Color(0xFF66BB6A), Color(0xFF9CCC65), Color(0xFFD4E157),
        Color(0xFFFFEE58), Color(0xFFFFCA28), Color(0xFFFFA726),
        Color(0xFFFF7043)
    )

    fun obtenerUsuario(id: String) {
        val usuarioLocal = UsuarioApiRepo.usuario.find { it.id == id }

        if (usuarioLocal != null) {
            // Si existe en la lista local, actualizamos la UI con sus datos
            _uiState.value = ListaUsuarioUIState(
                listaUsuarios = listOf(
                    UsuarioUIState(
                        usuarioLocal.id,
                        usuarioLocal.name,
                        usuarioLocal.email,
                        usuarioLocal.passwd ?: ""
                    )
                )
            )

            if (usuarioLocal.color != 0) {
                _colorPerfil.value = Color(usuarioLocal.color)
            }
            return
        }
        repo.read(
            id,
            onSucess = { dto ->
                dto?.let {
                    _uiState.value = ListaUsuarioUIState(
                        listaUsuarios = listOf(
                            UsuarioUIState(it.id, it.name, it.email, it.passwd ?: "")
                        )
                    )

                    if (it.color == 0) {
                        val nuevoColor = coloresFondo.random()
                        it.color = nuevoColor.toArgb()
                        repo.update(it, {

                        }, {})
                        _colorPerfil.value = nuevoColor
                    } else {
                        _colorPerfil.value = Color(it.color)
                    }
                }
            },
            onError = {}
        )
    }

    fun actualizarNombre(id: String, nuevoNombre: String) {
        val userUI = _uiState.value.listaUsuarios.find { it.id == id }
        userUI?.let {
            val dto = UsuarioDTORetroFit(
                it.id,
                nuevoNombre,
                it.correo,
                it.contraseña,
                color = _colorPerfil.value.toArgb()
            )
            repo.update(dto, onSucess = { obtenerUsuario(id) }, onError = {})
        }
    }

    fun actualizarCorreo(id: String, nuevoCorreo: String) {
        val userUI = _uiState.value.listaUsuarios.find { it.id == id }
        userUI?.let {
            val dto = UsuarioDTORetroFit(
                it.id,
                it.nombre,
                nuevoCorreo,
                it.contraseña,
                color = _colorPerfil.value.toArgb()
            )
            repo.update(dto, onSucess = { obtenerUsuario(id) }, onError = {})
        }
    }

    fun actualizarContrasenha(id: String, nuevaContrasenha: String) {
        val userUI = _uiState.value.listaUsuarios.find { it.id == id }
        userUI?.let {
            val dto = UsuarioDTORetroFit(
                it.id,
                it.nombre,
                it.correo,
                nuevaContrasenha,
                color = _colorPerfil.value.toArgb()
            )
            repo.update(dto, onSucess = { obtenerUsuario(id) }, onError = {})
        }
    }

    fun eliminarUsuario(id: String, onEliminado: () -> Unit) {
        repo.delete(id, onSucess = {
            _uiState.value = ListaUsuarioUIState(emptyList())
            onEliminado()
        }, onError = {})
    }
}
