package com.easyhz.daypet.sign.view.pet

import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.sign.contract.pet.PetIntent
import com.easyhz.daypet.sign.contract.pet.PetSideEffect
import com.easyhz.daypet.sign.contract.pet.PetState
import com.easyhz.daypet.sign.contract.pet.PetState.Companion.MEMO_MAX
import com.easyhz.daypet.sign.util.PetStep
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(

): BaseViewModel<PetState, PetIntent, PetSideEffect>(
    initialState = PetState.init()
) {
    override fun handleIntent(intent: PetIntent) {
        when(intent) {
            is PetIntent.InitPetScreen -> { initPetScreen() }
            is PetIntent.ChangePetNameText -> { onChangePetNameText(intent.newText) }
            is PetIntent.ClickNextButton -> { onClickNextButton() }
            is PetIntent.ChangeBreedText -> { onChangeBreedText(intent.newText)}
            is PetIntent.ClickBackButton -> { onClickBackButton() }
            is PetIntent.ClickChipButton -> { onClickChipButton(intent.clickIndex) }
            is PetIntent.ChangeMemoText -> { onChangeMemoText(intent.newText) }
            is PetIntent.ClickField -> { onClickField() }
            is PetIntent.FocusMemoField -> { onFocusMemoField(intent.isFocused) }
        }
    }

    private fun initPetScreen() {
        if (currentState.progress != 0f) return
        reduce { copy(progress = PetStep.firstProgress) }
    }
    private fun onChangePetNameText(newText: String) {
        val isButtonEnabled = newText.isNotBlank()
        reduce { copy(petName = newText, isButtonEnabled = isButtonEnabled) }
    }

    private fun onChangeBreedText(newText: String) {
        val isButtonEnabled = newText.isNotBlank()
        reduce { copy(breed = newText, isButtonEnabled = isButtonEnabled) }
    }

    private fun onClickNextButton() {
        currentState.step.currentStep.nextStep()?.let { nextStep ->
            reduce { updateProgressAndStep(currentStep = nextStep, progress = progress + PetStep.stepProgress) }
        } ?: savePetProfile()
    }

    private fun onClickBackButton() {
        currentState.step.currentStep.beforeStep()?.let { beforeStep ->
            reduce { updateProgressAndStep(currentStep = beforeStep, progress = progress - PetStep.stepProgress) }
        } ?: savePetProfile()
    }

    private fun savePetProfile() {
        // save Pet Profile
        println("save Pet")
    }

    private fun onClickChipButton(clickIndex: Int) {
        reduce { updateChipTags(clickIndex = clickIndex) }
    }

    private fun onChangeMemoText(newText: String) {
        if (newText.length > MEMO_MAX) return
        reduce { copy(memo = newText) }
        postSideEffect { PetSideEffect.ScrollToBottom }
    }

    private fun onClickField() {
        if(currentState.isFocusedMemo) {
            postSideEffect { PetSideEffect.OpenKeyboard }
        } else {
            postSideEffect { PetSideEffect.RequestFocus }
        }
    }
    private fun onFocusMemoField(isFocused: Boolean) {
        reduce { copy(isFocusedMemo = isFocused) }
    }
}