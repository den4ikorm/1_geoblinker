package org.example.geoblinker.core.platform

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * iOS реализация PlatformViewModel
 * ВАЖНО: Мы используем MainScope и обязательно отменяем его при очистке
 */
actual abstract class PlatformViewModel {
    actual val coroutineScope: CoroutineScope = MainScope()

    actual open fun onCleared() {
        coroutineScope.cancel()
    }
}
