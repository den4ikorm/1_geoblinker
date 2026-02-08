package org.example.geoblinker.presentation.features.auth

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.geoblinker.domain.repository.AuthRepository

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun login(user: String, pass: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val success = repository.login(user, pass)
            _isLoading.value = false
            if (success) onSuccess()
        }
    }
}
