package com.example.nexolap.vista.Navegacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nexolap.viewmodel.vm.AppNavigationVM
import com.example.nexolap.vista.Pages.LoginPage
import com.example.nexolap.vista.Pages.PrincipalPage
import com.example.nexolap.vista.Pages.RegistroPage
import com.example.nexolap.vista.Pages.PerfilPage
import com.example.nexolap.vista.Pages.BusquedaPage

@Composable
fun AppNavigation(
    vm: AppNavigationVM = viewModel()
) {
    val navController = rememberNavController()
    val uiState by vm.uiState.collectAsState()

    vm.checkUserSession()
    if (uiState.isCheckUserSession) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        NavHost(navController = navController, startDestination = uiState.initialRoute) {
            composable("login") {
                LoginPage(
                    onLoginSuccess = { userId ->
                        navController.navigate("principal/$userId") {
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
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                PrincipalPage(
                    onHomeClick = { /* Ya estamos en home */ },
                    onSearchClick = { navController.navigate("busqueda/$userId") },
                    onProfileClick = { navController.navigate("perfil/$userId") }
                )
            }
            composable(
                route = "busqueda/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                BusquedaPage(
                    onHomeClick = { navController.navigate("principal/$userId") },
                    onSearchClick = { /* Ya estamos en búsqueda */ },
                    onProfileClick = { navController.navigate("perfil/$userId") }
                )
            }
            composable(
                route = "perfil/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                PerfilPage(
                    userId = userId,
                    onHomeClick = { navController.navigate("principal/$userId") },
                    onSearchClick = { navController.navigate("busqueda/$userId") },
                    onProfileClick = { /* Ya estamos en perfil */ }
                )
            }
        }
    }
}
