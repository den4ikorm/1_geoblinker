package org.example.geoblinker.presentation.features.support
import org.example.geoblinker.core.base.*
data class SupportState(val isLoading: Boolean = false) : ViewState
sealed class SupportEvent : ViewEvent
sealed class SupportEffect : ViewEffect
