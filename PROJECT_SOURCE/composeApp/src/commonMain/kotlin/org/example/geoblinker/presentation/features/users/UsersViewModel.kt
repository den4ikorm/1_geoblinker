package org.example.geoblinker.presentation.features.users

import org.example.geoblinker.core.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsersViewModel : BaseViewModel<Any, Any, Any>(Any()) {
    fun loadData() {
        updateState { it } // Заглушка под БЕТОН
    }
}
