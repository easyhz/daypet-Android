package com.easyhz.daypet.sign.contract.auth

import android.content.Context
import com.easyhz.daypet.common.base.UiIntent

sealed class AuthIntent: UiIntent() {
    data class ClickSignInWithGoogle(val context: Context): AuthIntent()
    data class ChangeNameText(val newText: String) : AuthIntent()
    data object ClickProfileNextButton : AuthIntent()
}