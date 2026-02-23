package com.example.nexolap.Data

/**
 * Representa un usuario dentro del sistema Nexolap.
 *
 * @property UsuarioId Identificador único del usuario.
 * @property UsuarioNombre Nombre completo o de visualización del usuario.
 * @property UsuarioCorreo Dirección de correo electrónico del usuario.
 * @property UsuarioContrasenha Contraseña de acceso a la cuenta.
 * @property UsuarioKeepLogged Estado que indica si la sesión debe permanecer iniciada.
 * @property UsuarioColor Valor entero que representa el color de perfil o interfaz asociado al usuario.
 */
data class Usuario(
    val UsuarioId: Int,
    val UsuarioNombre: String,
    val UsuarioCorreo: String,
    val UsuarioContrasenha: String,
    val UsuarioKeepLogged: Boolean,
    val UsuarioColor: Int
)

/**
 * Representa la estructura de datos de un usuario tal como se recibe o envía a través de la API.
 *
 * Esta clase se utiliza para el mapeo de datos en las solicitudes de red, manteniendo
 * una estructura similar al modelo [Usuario] pero adaptando tipos de datos específicos
 * para la comunicación externa.
 *
 * @property UsuarioId Identificador único del usuario en formato String.
 * @property UsuarioNombre Nombre completo o nombre de usuario.
 * @property UsuarioCorreo Dirección de correo electrónico asociada a la cuenta.
 * @property UsuarioContrasenha Hash o cadena de la contraseña del usuario.
 * @property UsuarioKeepLogged Estado que indica si la sesión debe permanecer activa.
 * @property UsuarioColor Valor entero que representa la preferencia de color del perfil.
 */
data class UsuarioApi(
    val UsuarioId: String,
    val UsuarioNombre: String,
    val UsuarioCorreo: String,
    val UsuarioContrasenha: String,
    val UsuarioKeepLogged: Boolean,
    val UsuarioColor: Int
)
