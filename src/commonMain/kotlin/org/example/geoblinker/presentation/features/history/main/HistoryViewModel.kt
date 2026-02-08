package org.example.geoblinker.presentation.features.history.main

import org.example.geoblinker.core.base.BaseViewModel
import kotlinx.coroutines.launch

class HistoryViewModel : BaseViewModel<HistoryState, HistoryEvent, HistoryEffect>(HistoryState()) {
    override fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.OnDateSelected -> {
                updateState { copy(selectedDate = event.timestamp) }
                loadTrips()
            }
            is HistoryEvent.OnTripClick -> setEffect { HistoryEffect.NavigateToTrack(event.tripId) }
            is HistoryEvent.OnRefresh -> loadTrips()
        }
    }

    private fun loadTrips() {
        updateState { copy(isLoading = true) }
        // Здесь будет логика загрузки из репозитория
        updateState { copy(isLoading = false) }
    }
}
