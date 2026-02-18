package com.example.nexolap.viewmodel.uistate

import com.example.nexolap.Data.Usuario

data class RegistroUIState(
    val usuario: Usuario = Usuario(0, "", "", ""),
    val repitaContrasenha: String = "",
    val isLoading: Boolean = false,
    val registrationSuccess: Boolean = false,
    val error: String? = null
)
