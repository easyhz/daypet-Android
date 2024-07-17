package com.easyhz.daypet.sign.contract.pet

import android.net.Uri
import androidx.compose.foundation.ScrollState
import androidx.compose.ui.focus.FocusRequester
import com.easyhz.daypet.common.base.UiIntent
import com.easyhz.daypet.sign.component.BottomSheetItem
import com.easyhz.daypet.sign.view.pet.Gender
import java.time.LocalDate

sealed class PetIntent: UiIntent() {
    data class InitPetScreen(val groupId: String, val userId: String) : PetIntent()
    data object ClickProfile : PetIntent()
    data class PickImage(val uri: Uri?) : PetIntent()
    data class ChangePetNameText(val newText: String) : PetIntent()
    data class ChangeBreedText(val newText: String) : PetIntent()
    data class ChangeDate(val localDate: LocalDate) : PetIntent()
    data class ChangeGender(val gender: Gender) : PetIntent()
    data object ClickNextButton : PetIntent()
    data object ClickBackButton : PetIntent()
    data class ClickChipButton(val clickIndex: Int) : PetIntent()
    data class ChangeMemoText(val newText: String, val scrollState: ScrollState) : PetIntent()
    data class ClickField(val focusRequester: FocusRequester) : PetIntent()
    data class FocusMemoField(val isFocused: Boolean) : PetIntent()
    data object ClickDialogPositiveButton: PetIntent()
    data object ClickDialogNegativeButton: PetIntent()
    data object HideBottomSheet: PetIntent()
    data class ClickBottomSheetItem(val bottomSheetItem: BottomSheetItem): PetIntent()
}
