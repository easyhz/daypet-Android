package com.easyhz.daypet.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel<State: UiState, Intent: UiIntent, SideEffect: UiSideEffect>(
    initialState: State
): ViewModel() {
    val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState: StateFlow<State>
        get() = _uiState.asStateFlow()

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()
    val intent = _intent.asSharedFlow()

    private val _sideEffect: Channel<SideEffect> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()
    init {
        subscribeIntent()
    }

    /**
     * Intent 구독
     */
    private fun subscribeIntent() = viewModelScope.launch {
        intent.collect { handleIntent(it) }
    }

    /**
     * Intent 핸들러
     */
    abstract fun handleIntent(intent: UiIntent)

    /**
     * State 설정
     */
    fun reduce(reducer: State.() -> State) { _uiState.value = currentState.reducer() }

    /**
     * Intent 설정
     */
    fun postIntent(intent: Intent) = viewModelScope.launch { _intent.emit(intent) }

    /**
     * SideEffect 설정
     */
    fun postSideEffect(builder: () -> SideEffect) = viewModelScope.launch { _sideEffect.send(builder()) }
}