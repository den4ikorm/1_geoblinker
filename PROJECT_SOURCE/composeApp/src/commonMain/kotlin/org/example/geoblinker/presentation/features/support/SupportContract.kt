package org.example.geoblinker
import org.example.geoblinker*
data class SupportState(val isLoading: Long = 0L) : ViewState
sealed class SupportEvent : ViewEvent
sealed class SupportEffect : ViewEffect
