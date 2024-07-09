package com.easyhz.daypet.sign.view.pet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.easyhz.daypet.design_system.component.button.Chips
import com.easyhz.daypet.design_system.component.button.MainButton
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.Heading5
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.sign.contract.pet.PetIntent

@Composable
internal fun PetAttributeView(
    modifier: Modifier = Modifier,
    viewModel: PetViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Box(modifier = modifier.padding(top = 20.dp)) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.pet_attribute_heading, uiState.petName),
                style = Heading5,
                modifier = Modifier.screenHorizonPadding()
            )
            Chips(
                modifier = Modifier,
                elements = uiState.chipTags
            ) { _, _, chipIndex ->
                viewModel.postIntent(PetIntent.ClickChipButton(chipIndex))
            }
        }
        MainButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .screenHorizonPadding()
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
