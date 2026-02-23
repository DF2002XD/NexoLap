package com.example.nexolap.modelo

/**
 * Objeto de Transferencia de Datos (DTO) que representa a un usuario dentro de la aplicación.
 *
 * @property id Identificador único del usuario.
 * @property nombre Nombre completo o nombre de usuario.
 * @property correo Dirección de correo electrónico del usuario.
 * @property contraseña Contraseña de acceso a la cuenta.
 * @property keepLogged Indica si el usuario desea mantener la sesión iniciada. Por defecto es false.
 * @property color Valor entero que representa el color asignado al perfil del usuario.
 *                 El valor 0 indica que aún no tiene un color asignado.
 */
data class UsuarioDTO(
    var id: Int,
    var nombre: String,
    var correo: String,
    var contraseña: String,
    var keepLogged: Boolean = false,
    var color: Int = 0 // 0 significa que no tiene color asignado aún
)


/**
 * Objeto de Transferencia de Datos (DTO) utilizado para la comunicación con la API a través de Retrofit.
 *
 * Esta clase representa la estructura de los datos del usuario tal como se reciben o envían
 * a los servicios web, permitiendo el mapeo de los campos JSON del backend.
 *
 * @property id El identificador único del usuario en el sistema remoto (representado como String).
 * @property name El nombre completo del usuario.
 * @property email La dirección de correo electrónico del usuario.
 * @property passwd La contraseña del usuario (opcional en ciertas respuestas de la API).
 * @property keepLogged Indica si la sesión del usuario debe permanecer activa.
 * @property color Valor entero que representa el color de perfil asignado al usuario.
 */
data class UsuarioDTORetroFit(
    var id: String,
    var name: String,
    var email: String,
    var passwd: String? = "",
    var keepLogged: Boolean = false,
    var color: Int = 0
)
