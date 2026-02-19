package com.example.nexolap.viewmodel.vm

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.example.nexolap.Data.repository.UsuarioRepo
import com.example.nexolap.modelo.UsuarioDTO
import com.example.nexolap.viewmodel.uistate.ListaUsuarioUIState1
import com.example.nexolap.viewmodel.uistate.UsuarioUIState1
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PerfilPageVM : ViewModel() {

    private val _uiState = MutableStateFlow(ListaUsuarioUIState1())
    val uiState: StateFlow<ListaUsuarioUIState1> = _uiState.asStateFlow()

    private val repo: UsuarioRepo = UsuarioRepo.getInstance()

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

    fun obtenerUsuario(id: Int) {
        repo.read(
            id,
            onSucess = { dto ->
                dto?.let {
                    _uiState.value = ListaUsuarioUIState1(
                        listaUsuarios = listOf(
                            UsuarioUIState1(it.id, it.nombre, it.correo, it.contraseña)
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

    fun actualizarNombre(id: Int, nuevoNombre: String) {
        val userUI = _uiState.value.listaUsuarios.find { it.id == id }
        userUI?.let {
            val dto = UsuarioDTO(
                it.id,
                nuevoNombre,
                it.correo,
                it.contraseña,
                color = _colorPerfil.value.toArgb()
            )
            repo.update(dto, onSucess = { obtenerUsuario(id) }, onError = {})
        }
    }

    fun actualizarCorreo(id: Int, nuevoCorreo: String) {
        val userUI = _uiState.value.listaUsuarios.find { it.id == id }
        userUI?.let {
            val dto = UsuarioDTO(
                it.id,
                it.nombre,
                nuevoCorreo,
                it.contraseña,
                color = _colorPerfil.value.toArgb()
            )
            repo.update(dto, onSucess = { obtenerUsuario(id) }, onError = {})
        }
    }

    fun actualizarContrasenha(id: Int, nuevaContrasenha: String) {
        val userUI = _uiState.value.listaUsuarios.find { it.id == id }
        userUI?.let {
            val dto = UsuarioDTO(
                it.id,
                it.nombre,
                it.correo,
                nuevaContrasenha,
                color = _colorPerfil.value.toArgb()
            )
            repo.update(dto, onSucess = { obtenerUsuario(id) }, onError = {})
        }
    }

    fun eliminarUsuario(id: Int, onEliminado: () -> Unit) {
        repo.delete(id, onSucess = {
            _uiState.value = ListaUsuarioUIState1(emptyList())
            onEliminado()
        }, onError = {})
    }
}
