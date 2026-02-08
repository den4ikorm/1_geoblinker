package org.example.geoblinker
import org.example.geoblinker*
data class AboutState(val isLoading: Long = 0L) : ViewState
sealed class AboutEvent : ViewEvent
sealed class AboutEffect : ViewEffect
