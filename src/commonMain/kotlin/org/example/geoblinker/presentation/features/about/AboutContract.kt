package org.example.geoblinker.presentation.features.about
import org.example.geoblinker.core.base.*
data class AboutState(val isLoading: Boolean = false) : ViewState
sealed class AboutEvent : ViewEvent
sealed class AboutEffect : ViewEffect
