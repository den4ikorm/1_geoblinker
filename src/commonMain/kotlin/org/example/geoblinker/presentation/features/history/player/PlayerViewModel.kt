package org.example.geoblinker.presentation.features.history.player

import org.example.geoblinker.core.base.BaseViewModel
import kotlinx.coroutines.*

class PlayerViewModel : BaseViewModel<PlayerState, PlayerEvent, Nothing>(PlayerState()) {
    private var playerJob: Job? = null

    override fun onEvent(event: PlayerEvent) {
        when (event) {
            is PlayerEvent.OnTogglePlay -> if (state.value.isPlaying) pause() else play()
            is PlayerEvent.OnSeek -> updateState { copy(currentProgress = event.progress) }
            is PlayerEvent.OnSpeedChange -> updateState { copy(playbackSpeed = event.speed) }
        }
    }

    private fun play() {
        updateState { copy(isPlaying = true) }
        playerJob = scope.launch {
            while (state.value.currentProgress < 1f) {
                delay(100L / state.value.playbackSpeed)
                val newProgress = (state.value.currentProgress + 0.01f).coerceAtMost(1f)
                updateState { copy(currentProgress = newProgress) }
                if (newProgress >= 1f) pause()
            }
        }
    }

    private fun pause() {
        playerJob?.cancel()
        updateState { copy(isPlaying = false) }
    }
}
