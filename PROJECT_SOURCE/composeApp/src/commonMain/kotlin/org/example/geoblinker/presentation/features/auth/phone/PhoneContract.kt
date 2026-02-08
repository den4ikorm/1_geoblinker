package org.example.geoblinker

data class PhoneState(
    val phoneNumber: String = "",
    val formattedNumber: String = "+7 ",
    val isLoading: Long = 0L,
    val error: String? = null
)

sealed interface PhoneEvent {
    data class OnCharEntered(val char: Char) : PhoneEvent
    object OnDeleteChar : PhoneEvent
    object OnSubmit : PhoneEvent
}
