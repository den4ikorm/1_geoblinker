package org.example.geoblinker.presentation.features.auth

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.example.geoblinker.core.settings.SessionManager
import org.example.geoblinker.domain.repository.AuthRepository

class AuthViewModel(
    private val repository: AuthRepository,
    private val sessionManager: SessionManager
) : ScreenModel {

    val loginState = mutableStateOf("")
    val passState = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    val errorText = mutableStateOf<String?>(null)

    fun updateLogin(v: String) { loginState.value = v }
    fun updatePass(v: String) { passState.value = v }

    fun onLoginClick(onSuccess: () -> Unit) {
        screenModelScope.launch {
            isLoading.value = true
            errorText.value = null
            try {
                val response = repository.loginToBackend(loginState.value, passState.value)
                // Безопасная проверка: если token или sid пришли пустые, не пускаем дальше
                if (response?.token != null && response.sid != null) {
                    sessionManager.saveSession(response.token, response.sid)
                    onSuccess()
                } else {
                    errorText.value = "Ошибка: Неверный логин или пароль"
                }
            } catch (e: Exception) {
                errorText.value = "Ошибка сети: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
}
