package com.easyhz.daypet.sign

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.R
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.common.base.UiSideEffect
import com.easyhz.daypet.common.error.getMessageStringRes
import com.easyhz.daypet.domain.model.sign.LoginStep
import com.easyhz.daypet.domain.param.sign.UserInfoParam
import com.easyhz.daypet.domain.usecase.sign.CheckUserInfoUseCase
import com.easyhz.daypet.domain.usecase.sign.SaveUserInfoUseCase
import com.easyhz.daypet.domain.usecase.sign.SignInWithGoogleUseCase
import com.easyhz.daypet.sign.component.BottomSheetItem
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
    private val tag = this.javaClass.name
    override fun handleIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.ClickSignInWithGoogle -> { onClickSignInWithGoogle(intent.context) }
            is AuthIntent.ChangeNameText -> { onChangeNameText(intent.newText) }
            is AuthIntent.ClickProfileNextButton -> {
                onClickProfileNextButton()
                postSideEffect { AuthSideEffect.ClearFocus }
            }
            is AuthIntent.ClickProfile -> { onClickProfile() }
            is AuthIntent.PickImage -> { onPickImage(intent.uri) }
            is AuthIntent.ClickBottomSheetItem -> { onClickBottomSheetItem(intent.bottomSheetItem) }
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
                .onFailure { e ->
                    showErrorLog(e.message)
                    postSideEffect { AuthSideEffect.ShowSnackBar(e.getMessageStringRes()) }
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
        val param = UserInfoParam(uid = currentState.uid, name = currentState.name, thumbnailUrl = currentState.profileThumbnail.toString())
        setLoading(true)
        saveUserInfoUseCase.invoke(param)
            .onSuccess {
                postSideEffect { AuthSideEffect.NavigateToGroup(currentState.name, uid = currentState.uid) }
            }.onFailure { e ->
                showErrorLog(e.message)
                postSideEffect { AuthSideEffect.ShowSnackBar(e.getMessageStringRes()) }
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
            showErrorLog(e.message)
            postSideEffect { AuthSideEffect.ShowSnackBar(e.getMessageStringRes()) }
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
                        showErrorLog("Received an invalid google id token response.")
                        postSideEffect { AuthSideEffect.ShowSnackBar(R.string.google_login_error) }
                    }
                } else {
                    showErrorLog("Unexpected type of credential (custom).")
                    postSideEffect { AuthSideEffect.ShowSnackBar(R.string.google_login_unexpected_error) }
                }
            }
            else -> {
                showErrorLog("Unexpected type of credential (not custom).")
                postSideEffect { AuthSideEffect.ShowSnackBar(R.string.google_login_unexpected_error) }
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
                is LoginStep.NoGroup -> AuthSideEffect.NavigateToGroup(loginStep.name, uid)
                is LoginStep.ExistUser -> AuthSideEffect.NavigateToHome(loginStep.groupId, uid)
            }
            postSideEffect { sideEffect }
        }.onFailure { e ->
            postSideEffect { AuthSideEffect.ShowSnackBar(e.getMessageStringRes()) }
        }
    }

    private fun showErrorLog(message: String?) {
        Log.e(tag, message ?: "Unexpected.")
    }

    private fun onClickProfile() {
        if (currentState.profileThumbnail == Uri.EMPTY) {
            navigateToGallery()
        } else {
            showBottomSheet()
        }
    }

    private fun onPickImage(uri: Uri?) = viewModelScope.launch {
        uri?.let {
            reduce { copy(profileThumbnail = it) }
            if (currentState.isShowBottomSheet) {
                hideBottomSheet()
            }
        }
    }

    private fun navigateToGallery() {
        postSideEffect { AuthSideEffect.NavigateToGallery }
    }

    private fun showBottomSheet() {
        reduce { copy(isShowBottomSheet = true) }
    }

    private fun hideBottomSheet() {
        reduce { copy(isShowBottomSheet = false) }
    }

    private fun initProfileThumbnailUri() {
        reduce { copy(profileThumbnail = Uri.EMPTY) }
    }

    private fun onClickBottomSheetItem(bottomSheetItem: BottomSheetItem) {
        when(bottomSheetItem) {
            BottomSheetItem.SELECT -> { navigateToGallery() }
            BottomSheetItem.DELETE -> {
                initProfileThumbnailUri()
                hideBottomSheet()
            }
        }
    }
}