package com.example.nexolap.vista.Navegacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nexolap.Data.repository.UsuarioRepo
import com.example.nexolap.viewmodel.vm.AppNavigationVM
import com.example.nexolap.vista.Pages.LoginPage
import com.example.nexolap.vista.Pages.PrincipalPage
import com.example.nexolap.vista.Pages.RegistroPage
import com.example.nexolap.vista.Pages.PerfilPage
import com.example.nexolap.vista.Pages.BusquedaPage
import com.example.nexolap.vista.Pages.DetallesPage
import com.example.nexolap.vista.myComponents.ButtomAppBarNav
import com.example.nexolap.vista.myComponents.TopAppTitle

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    UsuarioRepo.init(context)

    val vm: AppNavigationVM = viewModel()
    val navController = rememberNavController()
    val uiState by vm.uiState.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    vm.checkUserSession()

    if (uiState.isCheckUserSession) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val userId = navBackStackEntry?.arguments?.getInt("userId") ?: 0

        val scaffoldConfig = mapOf(
            "principal/{userId}" to ScaffoldState(
                showTopBar = true,
                showBottomBar = true,
                title = "NexoLap"
            ),
            "busqueda/{userId}" to ScaffoldState(
                showTopBar = true,
                showBottomBar = true,
                title = "Búsqueda"
            ),
            "perfil/{userId}" to ScaffoldState(
                showTopBar = true,
                showBottomBar = true,
                title = "Perfil"
            ),
            "detalles/{ordenadorId}" to ScaffoldState(
                showTopBar = true,
                showBottomBar = false,
                title = ""
            )
        )

        val currentConfig = scaffoldConfig.entries.find { (route, _) ->
            currentRoute?.startsWith(route.split("/")[0]) == true
        }?.value ?: ScaffoldState(showTopBar = false, showBottomBar = false)

        Scaffold(
            topBar = {
                if (currentConfig.showTopBar) {
                    TopAppTitle(
                        title = currentConfig.title,
                        onBackClick = if (currentRoute?.startsWith("detalles") == true) {
                            { navController.popBackStack() }
                        } else null,
                        onLogoutClick = {
                            UsuarioRepo.getInstance().loggoutUSer(
                                onSucess = {
                                    navController.navigate("login") {
                                        popUpTo(0) { inclusive = true }
                                    }
                                },
                                onError = {}
                            )
                        },
                        onProfileDetailsClick = if (currentRoute?.startsWith("perfil") == true) {
                            null
                        } else {
                            { navController.navigate("perfil/$userId") }
                        }
                    )
                }
            },
            bottomBar = {
                if (currentConfig.showBottomBar) {
                    ButtomAppBarNav(
                        currentRoute = currentRoute,
                        onHomeClick = { navController.navigate("principal/$userId") },
                        onSearchClick = { navController.navigate("busqueda/$userId") },
                        onProfileClick = { navController.navigate("perfil/$userId") }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = uiState.initialRoute,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("login") {
                    LoginPage(
                        onLoginSuccess = { id ->
                            navController.navigate("principal/$id") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToRegister = {
                            navController.navigate("registro")
                        }
                    )
                }
                composable("registro") {
                    RegistroPage(
                        onNavigateToLogin = {
                            navController.navigate("login")
                        }
                    )
                }
                composable(
                    route = "principal/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) {
                    PrincipalPage(
                        onOrdenadorClick = { ordenadorId ->
                            navController.navigate("detalles/$ordenadorId")
                        }
                    )
                }
                composable(
                    route = "busqueda/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) {
                    BusquedaPage(
                        onOrdenadorClick = { ordenadorId ->
                            navController.navigate("detalles/$ordenadorId")
                        }
                    )
                }
                composable(
                    route = "perfil/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getInt("userId") ?: 0
                    PerfilPage(
                        userId = id,
                        onAccountDeleted = {
                            navController.navigate("login") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    )
                }
                composable(
                    route = "detalles/{ordenadorId}",
                    arguments = listOf(navArgument("ordenadorId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val ordenadorId = backStackEntry.arguments?.getInt("ordenadorId") ?: 0
                    DetallesPage(
                        ordenadorId = ordenadorId
                    )
                }
            }
        }
    }
}

data class ScaffoldState(
    val showTopBar: Boolean,
    val showBottomBar: Boolean,
    val title: String = ""
)
