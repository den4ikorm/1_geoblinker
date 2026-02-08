package org.example.geoblinker

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker
import org.example.geoblinker

data class MainState(val selectedTab: Int = 0) : UiState

class MainViewModel : BaseViewModel<MainState, UiEvent, UiEffect>(MainState()) {
    override fun onEvent(event: UiEvent) {}
    fun selectTab(index: Int) { updateState { it.copy(selectedTab = index) } }
}
