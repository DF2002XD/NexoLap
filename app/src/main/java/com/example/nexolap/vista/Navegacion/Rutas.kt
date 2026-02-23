package com.example.nexolap.vista.Navegacion

/**
 * Objeto que define las rutas de navegación de la aplicación y proporciona funciones auxiliares
 * para la construcción de rutas con argumentos dinámicos.
 *
 * Se utiliza principalmente en la configuración del NavHost y al realizar transiciones
 * entre pantallas mediante el NavController.
 */
object Rutas {
    const val Login = "login"
    const val Registro = "registro"

    // Rutas con argumentos (para el NavHost)
    const val Principal = "principal/{userId}"
    const val Busqueda = "busqueda/{userId}"
    const val Perfil = "perfil/{userId}"
    const val Detalles = "detalles/{ordenadorId}"

    // Funciones auxiliares para navegar (para el navController.navigate)
    fun principal(id: String) = "principal/$id"
    fun busqueda(id: String) = "busqueda/$id"
    fun perfil(id: String) = "perfil/$id"
    fun detalles(id: String) = "detalles/$id"
}