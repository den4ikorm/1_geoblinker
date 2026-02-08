package org.example.geoblinker.presentation.features.auth.phone

data class PhoneState(
    val phoneNumber: String = "",
    val formattedNumber: String = "+7 ",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface PhoneEvent {
    data class OnCharEntered(val char: Char) : PhoneEvent
    object OnDeleteChar : PhoneEvent
    object OnSubmit : PhoneEvent
}
