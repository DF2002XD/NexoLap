package com.example.nexolap.data

/**
 * Representa la estructura de datos de un usuario tal como se recibe o envía a través de la API.
 *
 * @property id Identificador único del usuario en formato String.
 * @property nombre Nombre completo o nombre de usuario.
 * @property correo Dirección de correo electrónico asociada a la cuenta.
 * @property contrasenha Hash o cadena de la contraseña del usuario.
 * @property keepLogged Estado que indica si la sesión debe permanecer activa.
 * @property color Valor entero que representa la preferencia de color del perfil.
 */
data class UsuarioApi(
    val id: String,
    val nombre: String,
    val correo: String,
    val contrasenha: String,
    val keepLogged: Boolean,
    val color: Int
)
