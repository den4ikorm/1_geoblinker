package org.example.geoblinker.presentation.features.auth

import org.example.geoblinker.presentation.core.*

data class AuthState(
    val phoneNumber: String = "",
    val formattedPhone: String = "+7 (___) ___-__-__",
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
) : UiState

sealed interface AuthEvent : UiEvent {
    data class OnPhoneChanged(val input: String) : AuthEvent
    object OnSendCodeClick : AuthEvent
}

sealed interface AuthEffect : UiEffect {
    object NavigateToSmsCode : AuthEffect
}
