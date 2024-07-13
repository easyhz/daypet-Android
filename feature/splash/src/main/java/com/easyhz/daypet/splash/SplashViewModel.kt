package com.easyhz.daypet.splash

import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.domain.model.sign.LoginStep
import com.easyhz.daypet.domain.usecase.sign.FetchLoginInfoUseCase
import com.easyhz.daypet.splash.contract.SplashIntent
import com.easyhz.daypet.splash.contract.SplashSideEffect
import com.easyhz.daypet.splash.contract.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val fetchLoginInfoUseCase: FetchLoginInfoUseCase
): BaseViewModel<SplashState, SplashIntent, SplashSideEffect>(
    initialState = SplashState
) {
    override fun handleIntent(intent: SplashIntent) { }

    init {
        fetchLoginInfo()
    }

    private fun fetchLoginInfo() = viewModelScope.launch {
        fetchLoginInfoUseCase.invoke(Unit).onSuccess { loginStep ->
            when(loginStep) {
                is LoginStep.NewUser -> { postSideEffect { SplashSideEffect.NavigateToLogin } }
                is LoginStep.NoGroup -> { postSideEffect { SplashSideEffect.NavigateToGroup(loginStep.name, loginStep.userId) }}
                is LoginStep.ExistUser -> { postSideEffect { SplashSideEffect.NavigateToHome(loginStep.groupId) } }
            }
        }.onFailure {
            postSideEffect { SplashSideEffect.NavigateToLogin }
        }
    }
}