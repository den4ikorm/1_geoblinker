package org.example.geoblinker.presentation.features.profile

import org.example.geoblinker.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class ProfileViewModel : BaseViewModel<ProfileState, ProfileEvent, ProfileEffect>(ProfileState()) {

    init {
        loadUserProfile()
    }

    override fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnLogoutClick -> sendEffect(ProfileEffect.NavigateToAuth)
            ProfileEvent.OnEditProfileClick -> sendEffect(ProfileEffect.ShowToast("Редактирование скоро появится"))
        }
    }

    private fun loadUserProfile() {
        updateState { it.copy(isLoading = true) }
        // Имитируем загрузку данных (позже заменим на репозиторий)
        scope.launch {
            val mockUser = ProfileUi(
                phone = "+7 (999) 000-00-00",
                userName = "Пользователь #1",
                appVersion = "1.0.5-beta",
                registrationDate = "06.02.2026"
            )
            updateState { it.copy(user = mockUser, isLoading = false) }
        }
    }
}
