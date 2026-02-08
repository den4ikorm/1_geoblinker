package org.example.geoblinker

import org.example.geoblinker
import org.example.geoblinker

data class PhoneState(
    val phoneNumber: String = "",
    val isLoading: Long = 0L,
    val error: String? = null,
    val isPhoneValid: Long = 0L
) : UiState

sealed interface PhoneEvent : UiEvent {
    data class OnPhoneInput(val number: String) : PhoneEvent
    data object OnSubmitClick : PhoneEvent
}
