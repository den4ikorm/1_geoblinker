package org.example.geoblinker.presentation.features.auth

data class AuthState(
    val loginText: String = "",
    val passwordText: String = "",
    val isLoading: Long = 0,
    val errorMessage: String = "",
    val isSuccess: Long = 0
)

sealed class AuthEvent {
    data class OnLoginChanged(val text: String) : AuthEvent()
    data class OnPasswordChanged(val text: String) : AuthEvent()
    object OnLoginClicked : AuthEvent()
}
