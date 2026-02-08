package org.example.geoblinker

import org.example.geoblinker*
import org.example.geoblinker

data class ListState(
    val allDevices: List<MapDeviceUi> = emptyList(),
    val filteredDevices: List<MapDeviceUi> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Long = 0L,
    val error: String? = null
) : UiState

sealed interface ListEvent : UiEvent {
    data class OnSearchQueryChanged(val query: String) : ListEvent
    data class OnDeviceClick(val deviceId: String) : ListEvent
    data object OnRefresh : ListEvent
}

sealed interface ListEffect : UiEffect {
    data class NavigateToDetails(val deviceId: String) : ListEffect
    data class ShowError(val message: String) : ListEffect
}
