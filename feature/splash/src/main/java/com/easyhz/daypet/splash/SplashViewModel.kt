package com.easyhz.daypet.splash

import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.domain.manager.UserManager
import com.easyhz.daypet.domain.model.sign.LoginStep
import com.easyhz.daypet.domain.param.member.GroupMemberParam
import com.easyhz.daypet.domain.usecase.member.FetchGroupMemberUseCase
import com.easyhz.daypet.domain.usecase.sign.FetchLoginInfoUseCase
import com.easyhz.daypet.splash.contract.SplashIntent
import com.easyhz.daypet.splash.contract.SplashSideEffect
import com.easyhz.daypet.splash.contract.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val fetchLoginInfoUseCase: FetchLoginInfoUseCase,
    private val fetchGroupMemberUseCase: FetchGroupMemberUseCase,
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
                is LoginStep.ExistUser -> { fetchUserInfo(loginStep.groupId, loginStep.userId) }
            }
        }.onFailure {
            postSideEffect { SplashSideEffect.NavigateToLogin }
        }
    }
    private fun fetchUserInfo(groupId: String, userId: String) = viewModelScope.launch {
        runCatching {
            val param = GroupMemberParam(groupId)
            UserManager.groupId = groupId
            UserManager.userId = userId
            UserManager.groupInfo = fetchGroupMemberUseCase.invoke(param).getOrNull()
        }.onSuccess {
            postSideEffect { SplashSideEffect.NavigateToHome(groupId) }
        }.onFailure {
            postSideEffect { SplashSideEffect.NavigateToLogin }
        }

    }
}