package org.example.geoblinker.presentation.features.main

import org.example.geoblinker.presentation.core.BaseViewModel

class RootViewModel : BaseViewModel<RootState, RootEvent, UiEffect>(RootState()) {
    override fun onEvent(event: RootEvent) {
        when (event) {
            is RootEvent.ChangeTab -> updateState { it.copy(currentTab = event.tab) }
            RootEvent.Logout -> updateState { it.copy(isAuthorized = false) }
        }
    }
}
