package com.example.nexolap.modelo

/**
 * Modelo de datos unificado para los usuarios.
 * Utiliza los nombres de campo requeridos por la API de Retrofit.
 *
 * @property id El identificador único del usuario.
 * @property name El nombre completo del usuario.
 * @property email La dirección de correo electrónico del usuario.
 * @property passwd La contraseña del usuario.
 * @property keepLogged Indica si la sesión del usuario debe permanecer activa.
 * @property color Valor entero que representa el color de perfil asignado al usuario.
 */
data class UsuarioDTO(
    val id: String,
    val name: String,
    val email: String,
    val passwd: String? = "",
    val keepLogged: Boolean = false,
    val color: Int = 0,
    val photoBase64: String? = null
)
