package com.example.nexolap.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexolap.Data.repository.UsuarioRepo
import com.example.nexolap.viewmodel.uistate.AppNavigationUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppNavigationVM() : ViewModel() {

    private val _uiState = MutableStateFlow(AppNavigationUIState(isCheckUserSession = true))
    val uiState: StateFlow<AppNavigationUIState> = _uiState.asStateFlow()

    private val userRepo: UsuarioRepo = UsuarioRepo.getInstance()

    fun checkUserSession() {
        viewModelScope.launch {
            val currentUser = userRepo.getCurrentUser()

            if (currentUser != null) {
                _uiState.update {
                    it.copy(
                        isCheckUserSession = false,
                        isUserLoggedIn = true,
                        initialRoute = "principal/${currentUser.id}"
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        isCheckUserSession = false,
                        isUserLoggedIn = false,
                        initialRoute = "login"
                    )
                }
            }
        }
    }

    fun logout(onSuccess: () -> Unit) {
        userRepo.loggoutUSer(
            onSucess = {
                _uiState.update { it.copy(isUserLoggedIn = false) }
                onSuccess()
            },
            onError = {}
        )
    }
}
