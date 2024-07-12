package com.easyhz.daypet.sign.view.pet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.easyhz.daypet.design_system.component.button.MainButton
import com.easyhz.daypet.design_system.component.image.ProfileImage
import com.easyhz.daypet.design_system.component.image.ProfileImageType
import com.easyhz.daypet.design_system.component.textField.BaseTextField
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubBody2
import com.easyhz.daypet.sign.contract.pet.PetIntent

@Composable
internal fun PetProfileView(
    modifier: Modifier = Modifier,
    viewModel: PetViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Box(
        modifier = modifier.screenHorizonPadding()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProfileImage(
                imageUri = uiState.profileThumbnail,
                type = ProfileImageType.Pet,
                modifier = Modifier
                    .padding(24.dp)
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                viewModel.postIntent(PetIntent.ClickProfile)
            }

            BaseTextField(
                value = uiState.petName,
                onValueChange = { newText -> viewModel.postIntent(PetIntent.ChangePetNameText(newText)) },
                title = stringResource(id = R.string.profile_name),
                placeholder = stringResource(id = R.string.profile_name_placeholder),
                singleLine = true,
                isFilled = false,
            )
            Text(
                text = stringResource(id = R.string.profile_name_caption),
                style = SubBody2
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
