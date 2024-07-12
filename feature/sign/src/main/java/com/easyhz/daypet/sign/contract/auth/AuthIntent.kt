package com.easyhz.daypet.sign.contract.auth

import android.content.Context
import android.net.Uri
import com.easyhz.daypet.common.base.UiIntent
import com.easyhz.daypet.sign.component.BottomSheetItem

sealed class AuthIntent: UiIntent() {
    data class ClickSignInWithGoogle(val context: Context): AuthIntent()
    data class ChangeNameText(val newText: String) : AuthIntent()
    data object ClickProfileNextButton : AuthIntent()
    data object ClickProfile : AuthIntent()
    data class PickImage(val uri: Uri?) : AuthIntent()
    data class ClickBottomSheetItem(val bottomSheetItem: BottomSheetItem): AuthIntent()
}