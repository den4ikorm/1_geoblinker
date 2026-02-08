package org.example.geoblinker

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.example.geoblinker

abstract class BaseViewModel<S : ViewState, E : ViewEvent, F : ViewEffect>(
    initialState: S
) : PlatformViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effect = Channel<F>()
    val effect: Flow<F> = _effect.receiveAsFlow()

    protected fun setState(reducer: S.() -> S) {
        _state.update(reducer)
    }

    protected fun setEffect(builder: () -> F) {
        val effectValue = builder()
        scope.launch { _effect.send(effectValue) }
    }

    abstract fun onEvent(event: E)
}
