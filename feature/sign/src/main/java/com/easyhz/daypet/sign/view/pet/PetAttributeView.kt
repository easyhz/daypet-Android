package com.easyhz.daypet.sign.view.pet

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.sign.contract.pet.PetIntent

@Composable
internal fun PetAttributeView(
    modifier: Modifier = Modifier,
    viewModel: PetViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Button(
        modifier = modifier.screenHorizonPadding(),
        onClick = { viewModel.postIntent(PetIntent.ClickNextButton) }) {

        Text(text = "PetAttribute VIew ${uiState.petName}")

    }
}
