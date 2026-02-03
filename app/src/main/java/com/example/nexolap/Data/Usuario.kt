package com.example.nexolap.Data

/**
 * Represents the data structure for a user.
 *
 * This data class holds the essential information for a user account,
 * including their name, email, and password.
 *
 * @property UsuarioNombre The user's full name.
 * @property UsuarioCorreo The user's email address, used for login and communication.
 * @property UsuarioContrasenha The user's password for account authentication.
 */
data class Usuario(
    val UsuarioNombre: String,
    val UsuarioCorreo: String,
    val UsuarioContrasenha: String
)
