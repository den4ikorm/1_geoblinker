package org.example.geoblinker.presentation.features.auth.phone

import org.example.geoblinker.presentation.core.UiEvent
import org.example.geoblinker.presentation.core.UiState

data class PhoneState(
    val phoneNumber: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isPhoneValid: Boolean = false
) : UiState

sealed interface PhoneEvent : UiEvent {
    data class OnPhoneInput(val number: String) : PhoneEvent
    data object OnSubmitClick : PhoneEvent
}
