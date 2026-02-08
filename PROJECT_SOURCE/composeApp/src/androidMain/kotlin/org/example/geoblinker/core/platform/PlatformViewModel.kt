package org.example.geoblinker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual abstract class PlatformViewModel : ViewModel() {
    actual val coroutineScope: CoroutineScope = viewModelScope
}
