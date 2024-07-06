package com.easyhz.daypet.sign.contract.pet

import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.sign.util.PetStep

data class PetState(
    val isLoading: Boolean,
    val progress: Float,
    val currentStep: PetStep,
    val petName: String,
    val isButtonEnabled: Boolean,
    val breed: String
): UiState() {
    companion object {
        fun init() = PetState(
            isLoading = false,
            progress = PetStep.firstProgress,
            currentStep = PetStep.PROFILE,
            petName = "",
            isButtonEnabled = false,
            breed = ""
        )
    }
}