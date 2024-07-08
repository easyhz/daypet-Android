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
            is PetIntent.InitPetScreen -> { initPetScreen() }
            is PetIntent.ChangePetNameText -> { onChangePetNameText(intent.newText) }
            is PetIntent.ClickNextButton -> { onClickNextButton() }
            is PetIntent.ChangeBreedText -> { onChangeBreedText(intent.newText)}
            is PetIntent.ClickBackButton -> { onClickBackButton() }
        }
    }

    private fun initPetScreen() {
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
}