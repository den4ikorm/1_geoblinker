package org.example.geoblinker.presentation.features.player

import org.example.geoblinker.core.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PlayerViewModel : BaseViewModel<Any, Any, Any>(Any()) {
    fun loadData() {
        updateState { it } // Заглушка под БЕТОН
    }
}
