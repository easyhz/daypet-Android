package com.easyhz.daypet.sign

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.common.base.UiSideEffect
import com.easyhz.daypet.domain.model.sign.LoginStep
import com.easyhz.daypet.domain.param.sign.UserInfoParam
import com.easyhz.daypet.domain.usecase.sign.CheckUserInfoUseCase
import com.easyhz.daypet.domain.usecase.sign.SaveUserInfoUseCase
import com.easyhz.daypet.domain.usecase.sign.SignInWithGoogleUseCase
import com.easyhz.daypet.sign.contract.auth.AuthIntent
import com.easyhz.daypet.sign.contract.auth.AuthSideEffect
import com.easyhz.daypet.sign.contract.auth.AuthState
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val saveUserInfoUseCase: SaveUserInfoUseCase,
    private val checkUserInfoUseCase: CheckUserInfoUseCase,
) : BaseViewModel<AuthState, AuthIntent, UiSideEffect>(
    initialState = AuthState.init()
) {
    override fun handleIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.ClickSignInWithGoogle -> { onClickSignInWithGoogle(intent.context) }
            is AuthIntent.ChangeNameText -> { onChangeNameText(intent.newText) }
            is AuthIntent.ClickProfileNextButton -> { onClickProfileNextButton() }
        }
    }

    private fun onClickSignInWithGoogle(context: Context) = viewModelScope.launch {
        setLoading(true)
        val result = showOneTapGoogleLogin(context = context)
        result?.let {
            signInWithGoogleUseCase.invoke(it)
                .onSuccess { user ->
                    reduce { copy(uid = user.uid) }
                    checkUserInfo(uid = user.uid)
                }
                .onFailure {
                    // TODO: Fail 처리
                }
        }.also {
            setLoading(false)
        }
    }

    private fun onChangeNameText(newText: String) {
        val isProfileButtonEnabled = newText.isNotBlank()
        reduce { copy(name = newText, isProfileButtonEnabled = isProfileButtonEnabled) }
    }

    private fun onClickProfileNextButton() = viewModelScope.launch {
        val param = UserInfoParam(uid = uiState.value.uid, name = uiState.value.name)
        setLoading(true)
        saveUserInfoUseCase.invoke(param)
            .onSuccess {
                postSideEffect { AuthSideEffect.NavigateToGroup }
            }.onFailure {
                // TODO Fail 처리
            }.also {
                setLoading(false)
            }
    }

    private suspend fun showOneTapGoogleLogin(context: Context): String? {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .build()

        val request: GetCredentialRequest =
            GetCredentialRequest.Builder().addCredentialOption(
                googleIdOption
            ).build()

        try {
            val result =
                CredentialManager.create(context)
                    .getCredential(context = context, request = request)
            return handleGoogleSignIn(result)
        } catch (e: Exception) {
            e.printStackTrace()
            // TODO 예외 처리
        }
        return null
    }

    private fun handleGoogleSignIn(result: GetCredentialResponse): String? {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
                        return googleIdTokenCredential.idToken
                    } catch (e: GoogleIdTokenParsingException) {
                        // TODO: 예외 처리
                        println("Received an invalid google id token response")
                    }
                } else {
                    // TODO: 예외 처리
                    // Catch any unrecognized custom credential type here.
                    println("Unexpected type of credential")
                }
            }
            else -> {
                println("Unexpected type of credential")
                //TODO: 예외 처리
            }
        }
        return null
    }

    private fun setLoading(isLoading: Boolean) {
        reduce { copy(isLoading = isLoading) }
    }
    private fun checkUserInfo(uid: String) = viewModelScope.launch {
        checkUserInfoUseCase(uid).onSuccess { loginStep ->
            val sideEffect = when(loginStep) {
                is LoginStep.NewUser -> AuthSideEffect.NavigateToProfile
                is LoginStep.NoGroup -> AuthSideEffect.NavigateToGroup
                is LoginStep.ExistUser -> AuthSideEffect.NavigateToGroup // FIXME 홈으로 이동
            }
            postSideEffect { sideEffect }
        }.onFailure {
            // TODO: Fail 처리
        }
    }
}