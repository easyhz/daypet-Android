package com.easyhz.daypet.sign.contract.pet

import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.sign.util.PetStep

data class PetState(
    val isLoading: Boolean,
    val progress: Float,
    val step: Step,
    val petName: String,
    val isButtonEnabled: Boolean,
    val breed: String
): UiState() {
    companion object {
        fun init() = PetState(
            isLoading = false,
            progress = 0f,
            step = Step(currentStep = PetStep.PROFILE, previousStep = null),
            petName = "",
            isButtonEnabled = false,
            breed = ""
        )
    }
    fun PetState.updateProgressAndStep(currentStep: PetStep, progress: Float): PetState {
        val updatedStepState = step.copy(
            previousStep = step.currentStep,
            currentStep = currentStep
        )
        return this.copy(
            step = updatedStepState,
            progress = progress
        )
    }
}

data class Step(
    val currentStep: PetStep,
    val previousStep: PetStep?
)