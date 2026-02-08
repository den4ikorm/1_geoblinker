package org.example.geoblinker.presentation.features.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.geoblinker.presentation.core.BaseViewModel
import org.example.geoblinker.presentation.core.UiState
import org.example.geoblinker.presentation.core.UiEvent
import org.example.geoblinker.presentation.core.UiEffect

data class MainState(val selectedTab: Int = 0) : UiState

class MainViewModel : BaseViewModel<MainState, UiEvent, UiEffect>(MainState()) {
    override fun onEvent(event: UiEvent) {}
    fun selectTab(index: Int) { updateState { it.copy(selectedTab = index) } }
}
