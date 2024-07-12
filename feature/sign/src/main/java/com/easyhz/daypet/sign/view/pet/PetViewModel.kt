package com.easyhz.daypet.sign.view.pet

import android.net.Uri
import androidx.compose.foundation.ScrollState
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.common.error.getMessageStringRes
import com.easyhz.daypet.domain.model.member.Pet
import com.easyhz.daypet.domain.param.member.PetInsertParam
import com.easyhz.daypet.domain.usecase.member.InsertPetInGroupUseCase
import com.easyhz.daypet.sign.contract.pet.PetIntent
import com.easyhz.daypet.sign.contract.pet.PetSideEffect
import com.easyhz.daypet.sign.contract.pet.PetState
import com.easyhz.daypet.sign.contract.pet.PetState.Companion.MEMO_MAX
import com.easyhz.daypet.sign.util.PetStep
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val insertPetInGroupUseCase: InsertPetInGroupUseCase
): BaseViewModel<PetState, PetIntent, PetSideEffect>(
    initialState = PetState.init()
) {
    override fun handleIntent(intent: PetIntent) {
        when(intent) {
            is PetIntent.InitPetScreen -> { initPetScreen(intent.groupId) }
            is PetIntent.ClickProfile -> { onClickProfile() }
            is PetIntent.PickImage -> { onPickImage(intent.uri) }
            is PetIntent.ChangePetNameText -> { onChangePetNameText(intent.newText) }
            is PetIntent.ClickNextButton -> { onClickNextButton() }
            is PetIntent.ChangeBreedText -> { onChangeBreedText(intent.newText)}
            is PetIntent.ChangeDate -> { onChangeDate(intent.localDate)}
            is PetIntent.ChangeGender -> { onChangeGender(intent.gender)}
            is PetIntent.ClickBackButton -> { onClickBackButton() }
            is PetIntent.ClickChipButton -> { onClickChipButton(intent.clickIndex) }
            is PetIntent.ChangeMemoText -> { onChangeMemoText(intent.newText, intent.scrollState) }
            is PetIntent.ClickField -> { onClickField(intent.focusRequester) }
            is PetIntent.FocusMemoField -> { onFocusMemoField(intent.isFocused) }
            is PetIntent.ClickDialogPositiveButton -> { onClickDialogPositiveButton() }
            is PetIntent.ClickDialogNegativeButton -> { onClickDialogNegativeButton() }
        }
    }

    private fun initPetScreen(groupId: String) {
        if (currentState.progress != 0f) return
        reduce { copy(progress = PetStep.firstProgress, groupId = groupId) }
    }
    private fun onClickProfile() {
        postSideEffect { PetSideEffect.NavigateToGallery }
    }
    private fun onPickImage(uri: Uri?) {
        uri?.let {
            reduce { copy(profileThumbnail = it) }
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

    private fun onChangeDate(localDate: LocalDate) {
        reduce { copy(birthTime = localDate) }
    }

    private fun onChangeGender(gender: Gender) {
        reduce { copy(gender = gender) }
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

    private fun savePetProfile() = viewModelScope.launch {
        val param = PetInsertParam(
            groupId = currentState.groupId,
            petList = listOf(
                Pet(
                    birthTime = currentState.birthTime,
                    breed = currentState.breed,
                    name = currentState.petName,
                    gender = currentState.gender.type,
                    thumbnailUrl = "",
                    memo = currentState.memo,
                    attributes = currentState.chipTags.filter { it.isSelected.value }.map { it.label }
                )
            )
        )
        insertPetInGroupUseCase.invoke(param)
            .onSuccess {
                reduce { copy(isOpenPetDialog = true) }
            }.onFailure { e ->
                postSideEffect { PetSideEffect.ShowSnackBar(e.getMessageStringRes()) }
            }
    }

    private fun onClickChipButton(clickIndex: Int) {
        reduce { updateChipTags(clickIndex = clickIndex) }
    }

    private fun onChangeMemoText(newText: String, scrollState: ScrollState) {
        if (newText.length > MEMO_MAX) return
        reduce { copy(memo = newText) }
        postSideEffect { PetSideEffect.ScrollToBottom(scrollState) }
    }

    private fun onClickField(focusRequester: FocusRequester) {
        if(currentState.isFocusedMemo) {
            postSideEffect { PetSideEffect.OpenKeyboard }
        } else {
            postSideEffect { PetSideEffect.RequestFocus(focusRequester) }
        }
    }
    private fun onFocusMemoField(isFocused: Boolean) {
        reduce { copy(isFocusedMemo = isFocused) }
    }

    private fun onClickDialogPositiveButton() {
        reduce { copy(isOpenPetDialog = false) }
//        postSideEffect { PetSideEffect.NavigateToPet(currentState.groupId) }
    }

    private fun onClickDialogNegativeButton() {
        reduce { copy(isOpenPetDialog = false) }
        postSideEffect { PetSideEffect.NavigateToHome(currentState.groupId) }
    }
}