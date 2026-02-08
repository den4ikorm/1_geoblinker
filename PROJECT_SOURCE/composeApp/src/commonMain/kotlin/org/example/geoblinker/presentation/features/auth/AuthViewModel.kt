package org.example.geoblinker.presentation.features.auth

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.example.geoblinker.domain.repository.AuthRepository

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.OnLoginChanged -> _state.update { it.copy(loginText = event.text) }
            is AuthEvent.OnPasswordChanged -> _state.update { it.copy(passwordText = event.text) }
            AuthEvent.OnLoginClicked -> login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = 1) }
            val result = repository.login(_state.value.loginText, _state.value.passwordText)
            if (result.isSuccess) {
                _state.update { it.copy(isLoading = 0, isSuccess = 1) }
            } else {
                _state.update { it.copy(isLoading = 0, errorMessage = "Ошибка входа") }
# 1. ПОДГОТОВКА ЛОГА
LOG_DIR="/storage/emulated/0/Documents/BACKUP"
echo "# ЭТАП: VIEWMODELS (ЛОГИКА ЭКРАНОВ)" > /storage/emulated/0/Documents/BACKUP/VIEWMODELS.md
echo "Дата: 2026-02-06 12:03" >> /storage/emulated/0/Documents/BACKUP/VIEWMODELS.md

# Путь к VM
VM_PATH="/data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features"

# 2. AUTH VIEWMODEL
cat <<EOF > /data/data/com.termux/files/home/WORKING_PROJECT/composeApp/src/commonMain/kotlin/org/example/geoblinker/presentation/features/auth/AuthViewModel.kt
package org.example.geoblinker.presentation.features.auth

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.example.geoblinker.domain.repository.AuthRepository

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.OnLoginChanged -> _state.update { it.copy(loginText = event.text) }
            is AuthEvent.OnPasswordChanged -> _state.update { it.copy(passwordText = event.text) }
            AuthEvent.OnLoginClicked -> login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = 1) }
            val result = repository.login(_state.value.loginText, _state.value.passwordText)
            if (result.isSuccess) {
                _state.update { it.copy(isLoading = 0, isSuccess = 1) }
            } else {
                _state.update { it.copy(isLoading = 0, errorMessage = "Ошибка входа") }
            }
        }
    }
}
