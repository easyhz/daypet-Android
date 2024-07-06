package com.easyhz.daypet.sign.contract.pet

import com.easyhz.daypet.common.base.UiIntent

sealed class PetIntent: UiIntent() {
    data class ChangePetNameText(val newText: String) : PetIntent()
    data class ChangeBreedText(val newText: String) : PetIntent()
    data object ClickNextButton : PetIntent()
}