package org.example.geoblinker.presentation.features.auth

import org.example.geoblinker.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel : BaseViewModel<AuthState, AuthEvent, AuthEffect>(AuthState()) {
    
    override fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.OnPhoneChanged -> {
                // Логика форматирования и валидации (пока простая)
                val cleanPhone = event.input.filter { it.isDigit() }
                updateState { it.copy(
                    phoneNumber = cleanPhone,
                    isButtonEnabled = cleanPhone.length == 11
                ) }
            }
            AuthEvent.OnSendCodeClick -> {
                sendCode()
            }
        }
    }

    private fun sendCode() {
        updateState { it.copy(isLoading = true) }
        // Имитируем запрос к API
        scope.launch {
            kotlinx.coroutines.delay(1000)
            updateState { it.copy(isLoading = false) }
            sendEffect(AuthEffect.NavigateToSmsCode)
        }
    }
}
