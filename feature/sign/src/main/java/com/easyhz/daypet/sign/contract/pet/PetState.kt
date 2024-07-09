package com.easyhz.daypet.sign.contract.pet

import androidx.compose.runtime.mutableStateOf
import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.design_system.util.button.ChipState
import com.easyhz.daypet.design_system.util.button.toChipState
import com.easyhz.daypet.sign.util.PetStep
import com.easyhz.daypet.sign.util.chipTags

data class PetState(
    val isLoading: Boolean,
    val progress: Float,
    val step: Step,
    val petName: String,
    val isButtonEnabled: Boolean,
    val breed: String,
    val chipTags: List<ChipState>,
    val memo: String,
    val isFocusedMemo: Boolean
): UiState() {
    companion object {
        const val MEMO_MAX = 500
        fun init() = PetState(
            isLoading = false,
            progress = 0f,
            step = Step(currentStep = PetStep.PROFILE, previousStep = null),
            petName = "",
            isButtonEnabled = false,
            breed = "",
            chipTags = chipTags.toChipState(),
            memo = "",
            isFocusedMemo = false
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

    fun PetState.updateChipTags(clickIndex: Int): PetState {
        val updatedChipTags = chipTags.mapIndexed { index, chipState ->
            if (index == clickIndex) {
                chipState.copy(isSelected = mutableStateOf(!chipState.isSelected.value))
            } else {
                chipState
            }
        }
        return this.copy(chipTags = updatedChipTags)
    }
}

data class Step(
    val currentStep: PetStep,
    val previousStep: PetStep?
)