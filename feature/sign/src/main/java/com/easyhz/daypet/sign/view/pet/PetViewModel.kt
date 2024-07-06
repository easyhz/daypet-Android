package com.easyhz.daypet.sign.view.pet

import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.sign.contract.pet.PetIntent
import com.easyhz.daypet.sign.contract.pet.PetSideEffect
import com.easyhz.daypet.sign.contract.pet.PetState
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
            is PetIntent.ChangePetNameText -> { onChangePetNameText(intent.newText) }
            is PetIntent.ClickNextButton -> { onClickNextButton() }
            is PetIntent.ChangeBreedText -> { onChangeBreedText(intent.newText)}
        }

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
        val currentStep = currentState.currentStep
        if(currentStep == PetStep.entries.last()) {
            savePetProfile()
            return
        }

        currentStep.nextStep()?.let { nextStep ->
            reduce { copy(currentStep = nextStep, progress = progress + PetStep.stepProgress) }
        }
    }

    private fun savePetProfile() {
        // 저장 로직
    }
}