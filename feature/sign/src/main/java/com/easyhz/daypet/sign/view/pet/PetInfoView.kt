package com.easyhz.daypet.sign.view.pet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.button.DayPetSegmentedButton
import com.easyhz.daypet.design_system.component.button.MainButton
import com.easyhz.daypet.design_system.component.button.SegmentedType
import com.easyhz.daypet.design_system.component.picker.DatePickerButton
import com.easyhz.daypet.design_system.component.textField.BaseTextField
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.Heading5
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubBody2
import com.easyhz.daypet.sign.contract.pet.PetIntent
import java.time.LocalDate

// TODO : 선택 로직
@Composable
internal fun PetInfoView(
    modifier: Modifier = Modifier,
    viewModel: PetViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = modifier.padding(top = 20.dp).screenHorizonPadding()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.pet_info_heading, uiState.petName),
                style = Heading5
            )
            InfoField(
                breed = uiState.breed,
                onValueChange = { newText -> viewModel.postIntent(PetIntent.ChangeBreedText(newText)) },
                onDateChange = { localDate ->
                    viewModel.postIntent(PetIntent.ChangeDate(localDate))
                },
                onClickSegmented = { gender ->
                    viewModel.postIntent(PetIntent.ChangeGender(gender))
                }
            )
        }

        MainButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            enabled = uiState.isButtonEnabled,
            text = stringResource(id = R.string.profile_name_next),
            contentColor = MainBackground,
            containerColor = Primary
        ) {
            viewModel.postIntent(PetIntent.ClickNextButton)
        }
    }
}

@Composable
private fun InfoField(
    breed: String,
    onValueChange: (String) -> Unit,
    onDateChange: (LocalDate) -> Unit,
    onClickSegmented: (Gender) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        BaseTextField(
            value = breed,
            onValueChange = onValueChange,
            title = stringResource(id = R.string.pet_info_breed),
            placeholder = stringResource(id = R.string.pet_info_breed_placeholder),
            singleLine = true,
            isFilled = false,
        )
        DatePickerView(
            onDateChange = onDateChange
        )
        SegmentedButtonView(
            onClickSegmented = onClickSegmented
        )
    }
}

@Composable
private fun DatePickerView(
    onDateChange: (LocalDate) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.pet_info_birth),
            style = SubBody2,
            color = Primary
        )
        DatePickerButton { onDateChange(it) }
    }
}

enum class Gender(
    val type: String,
) : SegmentedType {
    MALE(type = "Male") {
        override val labelId: Int
            get() = R.string.pet_info_male
    }, FEMALE(type = "Female") {
        override val labelId: Int
            get() = R.string.pet_info_female
    }
}

@Composable
private fun SegmentedButtonView(
    onClickSegmented: (Gender) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.pet_info_gender),
            style = SubBody2,
            color = Primary
        )
        DayPetSegmentedButton(
            modifier = Modifier.fillMaxWidth(),
            options = enumValues<Gender>(),
            onClick = onClickSegmented
        )
    }
}
