package com.example.nexolap.vista.navegacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nexolap.data.retrofit.repo.UsuarioApiRepo
import com.example.nexolap.viewmodel.vm.AppNavigationVM
import com.example.nexolap.vista.Pages.AddOrdenadorPage
import com.example.nexolap.vista.Pages.BusquedaPage
import com.example.nexolap.vista.Pages.DetallesPage
import com.example.nexolap.vista.Pages.LoginPage
import com.example.nexolap.vista.Pages.PerfilPage
import com.example.nexolap.vista.Pages.PrincipalPage
import com.example.nexolap.vista.Pages.RegistroPage
import com.example.nexolap.vista.navegacion.Rutas.AddOrdenador
import com.example.nexolap.vista.navegacion.Rutas.Busqueda
import com.example.nexolap.vista.navegacion.Rutas.Detalles
import com.example.nexolap.vista.navegacion.Rutas.Login
import com.example.nexolap.vista.navegacion.Rutas.Perfil
import com.example.nexolap.vista.navegacion.Rutas.Principal
import com.example.nexolap.vista.navegacion.Rutas.Registro
import com.example.nexolap.vista.navegacion.Rutas.addOrdenador
import com.example.nexolap.vista.navegacion.Rutas.busqueda
import com.example.nexolap.vista.navegacion.Rutas.detalles
import com.example.nexolap.vista.navegacion.Rutas.perfil
import com.example.nexolap.vista.navegacion.Rutas.principal
import com.example.nexolap.vista.myComponents.ButtomAppBarNav
import com.example.nexolap.vista.myComponents.TopAppTitle


/**
 * Composable principal que gestiona la navegación de la aplicación NexoLap.
 *
 * Esta función inicializa el repositorio de usuarios, gestiona el estado de la sesión
 * del usuario y define la estructura de navegación mediante un [NavHost]. Configura
 * dinámicamente elementos de la interfaz de usuario como el [Scaffold], incluyendo
 * una barra superior (TopBar) y una barra inferior (BottomBar) según la ruta actual.
 *
 * El grafo de navegación incluye las siguientes rutas:
 * - Login y Registro: Para el acceso y creación de cuentas.
 * - Principal: Pantalla de inicio con listado de ordenadores.
 * - Búsqueda: Interfaz para buscar equipos específicos.
 * - Perfil: Gestión de los datos del usuario actual.
 * - Detalles: Vista detallada de un ordenador específico.
 *
 * Mientras se verifica la sesión del usuario, se muestra un indicador de carga circular.
 */
@Composable
fun AppNavigation() {
    val context = LocalContext.current
    UsuarioApiRepo.init(context)

    val vm: AppNavigationVM = viewModel()
    val navController = rememberNavController()
    val uiState by vm.uiState.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(Unit) {
        vm.checkUserSession()
    }

    if (uiState.isCheckUserSession) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val userId = uiState.userId ?: ""

        val scaffoldConfig = mapOf(
            Principal to ScaffoldState(
                showTopBar = true, showBottomBar = true, title = "NexoLap"
            ), Busqueda to ScaffoldState(
                showTopBar = true, showBottomBar = true, title = "Búsqueda"
            ), Perfil to ScaffoldState(
                showTopBar = true, showBottomBar = true, title = "Perfil"
            ), Detalles to ScaffoldState(
                showTopBar = true, showBottomBar = false, title = ""
            ), AddOrdenador to ScaffoldState(
                showTopBar = true, showBottomBar = true, title = "Añadir Ordenador"
            )
        )

        val currentConfig = scaffoldConfig.entries.find { (route, _) ->
            currentRoute?.startsWith(route.split("/")[0]) == true
        }?.value ?: ScaffoldState(showTopBar = false, showBottomBar = false)

        Scaffold(topBar = {
            if (currentConfig.showTopBar) {
                TopAppTitle(
                    title = currentConfig.title,
                    onBackClick = if (currentRoute?.startsWith("detalles") == true || currentRoute == AddOrdenador) {
                        { navController.popBackStack() }
                    } else null,
                    onLogoutClick = {
                        vm.logout(onSuccess = {
                            navController.navigate(Login) {
                                popUpTo(0) { inclusive = true }
                            }
                        })
                    },
                    onProfileDetailsClick = if (currentRoute?.startsWith("perfil") == true) {
                        null
                    } else {
                        { navController.navigate(perfil(userId)) }
                    },
                    profileColor = uiState.userColor?.let { if (it != 0) Color(it) else Color.Gray } ?: Color.Gray,
                    profileInitial = uiState.userName ?: "?"
                )
            }
        }, bottomBar = {
            if (currentConfig.showBottomBar) {
                ButtomAppBarNav(
                    currentRoute = currentRoute,
                    onHomeClick = { navController.navigate(principal(userId)) },
                    onSearchClick = { navController.navigate(busqueda(userId)) },
                    onAddClick = { navController.navigate(AddOrdenador) },
                    onProfileClick = { navController.navigate(perfil(userId)) })
            }
        }) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = uiState.initialRoute,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Login) {
                    LoginPage(onLoginSuccess = { id ->
                        vm.updateUserInfo()
                        navController.navigate(principal(id)) {
                            popUpTo(Login) { inclusive = true }
                        }
                    }, onNavigateToRegister = {
                        navController.navigate(Registro)
                    })
                }
                composable(Registro) {
                    RegistroPage(
                        onNavigateToLogin = {
                            navController.navigate(Login)
                        })
                }
                composable(
                    route = Principal,
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                ) { _ ->
                    PrincipalPage(
                        onOrdenadorClick = { ordenadorId ->
                            navController.navigate(detalles(ordenadorId))
                        }
                    )
                }
                composable(
                    route = Busqueda,
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                ) { _ ->
                    BusquedaPage(
                        onOrdenadorClick = { ordenadorId ->
                            navController.navigate(detalles(ordenadorId))
                        })
                }
                composable(
                    route = Perfil,
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("userId") ?: ""
                    PerfilPage(
                        userId = id, 
                        onAccountDeleted = {
                            navController.navigate(Login) {
                                popUpTo(0) { inclusive = true }
                            }
                        },
                        onUserUpdated = { vm.updateUserInfo() }
                    )
                }
                composable(
                    route = Detalles,
                    arguments = listOf(navArgument("ordenadorId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val ordenadorId = backStackEntry.arguments?.getString("ordenadorId") ?: ""
                    DetallesPage(
                        ordenadorId = ordenadorId
                    )
                }
                composable(AddOrdenador) {
                    AddOrdenadorPage(
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

/**
 * Representa el estado de configuración de la interfaz de usuario para una pantalla específica.
 *
 * Se utiliza para determinar dinámicamente si los componentes globales del [Scaffold]
 * (como la barra superior y la barra de navegación inferior) deben mostrarse,
 * así como el título que debe aparecer en la cabecera.
 *
 * @property showTopBar Define si se debe mostrar la [TopAppTitle] en la pantalla actual.
 * @property showBottomBar Define si se debe mostrar la [ButtomAppBarNav] en la pantalla actual.
 * @property title El texto que se mostrará como título en la barra superior. Por defecto es una cadena vacía.
 */
data class ScaffoldState(
    val showTopBar: Boolean, val showBottomBar: Boolean, val title: String = ""
)
