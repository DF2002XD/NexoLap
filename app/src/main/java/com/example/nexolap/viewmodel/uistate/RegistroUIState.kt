package com.example.nexolap.viewmodel.uistate

import com.example.nexolap.Data.Usuario
import com.example.nexolap.Data.UsuarioApi

/**
 * Representa o estado da interface de usuário (UI) para a tela de registro.
 *
 * @property usuario O objeto contendo as informações do usuário a ser cadastrado.
 * @property repitaContrasenha Campo para confirmação da senha digitada pelo usuário.
 * @property isLoading Indica se um processo de registro está em andamento (ex: chamada de API).
 * @property registrationSuccess Indica se o processo de registro foi concluído com sucesso.
 * @property error Armazena uma mensagem de erro caso ocorra uma falha durante o registro.
 */
data class RegistroUIState(
    val usuario: Usuario = Usuario(0, "", "", "", false, 0),
    val repitaContrasenha: String = "",
    val isLoading: Boolean = false,
    val registrationSuccess: Boolean = false,
    val error: String? = null
)

/**
 * Representa el estado de la interfaz de usuario (UI) durante el proceso de registro
 * utilizando el modelo de datos compatible con la API ([UsuarioApi]).
 *
 * @property usuario El objeto que contiene la información del usuario a registrar.
 * @property repitaContrasenha Campo para la confirmación de la contraseña ingresada.
 * @property isLoading Indica si hay una operación de registro en curso.
 * @property registrationSuccess Indica si el proceso de registro se completó correctamente.
 * @property error Mensaje de error en caso de que ocurra una falla durante el registro.
 */
data class RegistroUIStateApi(
    val usuario: UsuarioApi = UsuarioApi("", "", "", "", false, 0),
    val repitaContrasenha: String = "",
    val isLoading: Boolean = false,
    val registrationSuccess: Boolean = false,
    val error: String? = null
)
