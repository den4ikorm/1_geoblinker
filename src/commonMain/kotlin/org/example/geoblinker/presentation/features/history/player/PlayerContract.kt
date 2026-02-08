package org.example.geoblinker.presentation.features.history.player

import org.example.geoblinker.core.base.ViewState
import org.example.geoblinker.core.base.ViewEvent
import org.example.geoblinker.core.base.ViewEffect

data class PlayerState(
    val isPlaying: Boolean = false,
    val currentProgress: Float = 0f, // 0.0 to 1.0
    val playbackSpeed: Int = 1, // 1x, 2x, 4x, 8x
    val currentTimeText: String = "00:00",
    val currentSpeedText: String = "0 км/ч"
) : ViewState

sealed interface PlayerEvent : ViewEvent {
    data object OnTogglePlay : PlayerEvent
    data class OnSeek(val progress: Float) : PlayerEvent
    data class OnSpeedChange(val speed: Int) : PlayerEvent
}
