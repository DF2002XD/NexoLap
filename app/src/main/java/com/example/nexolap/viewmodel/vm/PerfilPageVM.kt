package com.example.nexolap.viewmodel.vm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Patterns
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexolap.data.retrofit.repo.IUsuarioApiRepo
import com.example.nexolap.data.retrofit.repo.UsuarioApiRepo
import com.example.nexolap.modelo.UsuarioDTO
import com.example.nexolap.viewmodel.uistate.ListaUsuarioUIState
import com.example.nexolap.viewmodel.uistate.UsuarioUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

/**
 * ViewModel encargado de gestionar la lógica de negocio y el estado de la interfaz de usuario
 * para la pantalla de perfil del usuario.
 */
class PerfilPageVM : ViewModel() {

    private val _uiState = MutableStateFlow(ListaUsuarioUIState())
    val uiState: StateFlow<ListaUsuarioUIState> = _uiState.asStateFlow()

    private val _colorPerfil = MutableStateFlow(Color.Gray)
    val colorPerfil: StateFlow<Color> = _colorPerfil.asStateFlow()

    private val repo: IUsuarioApiRepo = UsuarioApiRepo.getInstance()

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
            _uiState.value = ListaUsuarioUIState(
                listaUsuarios = listOf(
                    UsuarioUIState(
                        usuarioLocal.id,
                        usuarioLocal.name,
                        usuarioLocal.email,
                        usuarioLocal.passwd ?: "",
                        usuarioLocal.photoBase64
                    )
                )
            )

            if (usuarioLocal.color != 0) {
                _colorPerfil.value = Color(usuarioLocal.color)
            }
            return
        }
        viewModelScope.launch {
            repo.read(
                id,
                onSuccess = { dto ->
                    dto?.let {
                        _uiState.value = ListaUsuarioUIState(
                            listaUsuarios = listOf(
                                UsuarioUIState(
                                    it.id,
                                    it.name,
                                    it.email,
                                    it.passwd ?: "",
                                    it.photoBase64
                                )
                            )
                        )

                        if (it.color != 0) {
                            _colorPerfil.value = Color(it.color)
                        } else if (_colorPerfil.value == Color.Gray) {
                            val nuevoColor = coloresFondo.random()
                            val dtoActualizado = it.copy(color = nuevoColor.toArgb())
                            viewModelScope.launch {
                                repo.update(dtoActualizado, {}, {})
                            }
                            _colorPerfil.value = nuevoColor
                        }
                    }
                },
                onError = {
                    _uiState.update { it.copy(error = "Error al obtener los datos del usuario") }
                }
            )
        }
    }

    fun actualizarNombre(id: String, nuevoNombre: String) {
        val userUI = _uiState.value.listaUsuarios.find { it.id == id }
        userUI?.let {
            val dto = UsuarioDTO(
                id = it.id,
                name = nuevoNombre,
                email = it.correo,
                passwd = it.contrasenha,
                color = _colorPerfil.value.toArgb(),
                photoBase64 = it.foto
            )
            viewModelScope.launch {
                repo.update(dto, onSuccess = { obtenerUsuario(id) }, onError = {
                    _uiState.update { it.copy(error = "Error al actualizar el nombre") }
                })
            }
        }
    }

    fun actualizarCorreo(id: String, nuevoCorreo: String) {
        if (nuevoCorreo.isBlank()) {
            _uiState.update { it.copy(error = "El correo no puede estar vacío") }
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(nuevoCorreo).matches()) {
            _uiState.update { it.copy(error = "El formato del correo electrónico no es válido") }
            return
        }
        val userUI = _uiState.value.listaUsuarios.find { it.id == id }
        userUI?.let {
            val dto = UsuarioDTO(
                id = it.id,
                name = it.nombre,
                email = nuevoCorreo,
                passwd = it.contrasenha,
                color = _colorPerfil.value.toArgb(),
                photoBase64 = it.foto
            )
            viewModelScope.launch {
                repo.update(dto, onSuccess = { obtenerUsuario(id) }, onError = {
                    _uiState.update { it.copy(error = "Error al actualizar el correo") }
                })
            }
        }
    }

    fun actualizarContrasenha(id: String, nuevaContrasenha: String, repetirContrasenha: String) {
        if (nuevaContrasenha != repetirContrasenha) {
            _uiState.update { it.copy(error = "Las contraseñas no coinciden") }
            return
        }
        if (nuevaContrasenha.isBlank()) {
            _uiState.update { it.copy(error = "La contraseña no puede estar vacía") }
            return
        }

        val userUI = _uiState.value.listaUsuarios.find { it.id == id }
        userUI?.let {
            val dto = UsuarioDTO(
                id = it.id,
                name = it.nombre,
                email = it.correo,
                passwd = nuevaContrasenha,
                color = _colorPerfil.value.toArgb(),
                photoBase64 = it.foto
            )
            viewModelScope.launch {
                repo.update(dto, onSuccess = { 
                    _uiState.update { state -> state.copy(error = null) }
                    obtenerUsuario(id) 
                }, onError = {
                    _uiState.update { it.copy(error = "Error al actualizar la contraseña") }
                })
            }
        }
    }

    fun actualizarImagen(id: String, bitmap: Bitmap) {
        val userUI = _uiState.value.listaUsuarios.find { it.id == id }
        userUI?.let {
            viewModelScope.launch {
                try {
                    val base64 = bitmapToBase64(bitmap)
                    val dto = UsuarioDTO(
                        id = it.id,
                        name = it.nombre,
                        email = it.correo,
                        passwd = it.contrasenha,
                        color = _colorPerfil.value.toArgb(),
                        photoBase64 = base64
                    )
                    repo.update(dto, onSuccess = { obtenerUsuario(id) }, onError = {
                        _uiState.update { state -> state.copy(error = "Error al actualizar la imagen") }
                    })
                } catch (e: Exception) {
                    _uiState.update { state -> state.copy(error = "Error al procesar la imagen") }
                }
            }
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun eliminarUsuario(id: String, onEliminado: () -> Unit) {
        viewModelScope.launch {
            repo.delete(id, onSuccess = {
                _uiState.value = ListaUsuarioUIState(emptyList())
                onEliminado()
            }, onError = {
                _uiState.update { it.copy(error = "Error al eliminar el usuario") }
            })
        }
    }

    fun limpiarError() {
        _uiState.update { it.copy(error = null) }
    }
}
