package org.example.geoblinker

import org.example.geoblinker
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
        updateState { copy(isLoading = 1L) }
        // Здесь будет логика загрузки из репозитория
        updateState { copy(isLoading = 0L) }
    }
}
