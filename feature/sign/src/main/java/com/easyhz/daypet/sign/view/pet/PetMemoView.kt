package com.easyhz.daypet.sign.view.pet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.common.extension.collectInLaunchedEffectWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.button.MainButton
import com.easyhz.daypet.design_system.component.textField.ContentTextField
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.Heading5
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubBody2
import com.easyhz.daypet.sign.contract.pet.PetIntent
import com.easyhz.daypet.sign.contract.pet.PetSideEffect
import com.easyhz.daypet.sign.contract.pet.PetState.Companion.MEMO_MAX

@Composable
internal fun PetMemoView(
    modifier: Modifier = Modifier,
    viewModel: PetViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()

    Box(modifier = modifier
        .fillMaxSize()
        .padding(top = 20.dp)
        .screenHorizonPadding()
    ) {
        Column(
            modifier = Modifier
                .imePadding()
                .padding(bottom = 92.dp)
                .fillMaxHeight()
                .noRippleClickable { viewModel.postIntent(PetIntent.ClickField) },
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.pet_memo_heading, uiState.petName),
                style = Heading5
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.pet_memo_header) + "(${uiState.memo.length}/$MEMO_MAX)",
                    style = SubBody2,
                    color = Primary
                )
                MemoField(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            viewModel.postIntent(PetIntent.FocusMemoField(focusState.isFocused))
                        },
                    memo = uiState.memo,
                    onValueChange = { viewModel.postIntent(PetIntent.ChangeMemoText(it)) },
                )
            }
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


    viewModel.sideEffect.collectInLaunchedEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is PetSideEffect.ScrollToBottom -> {
                println("scrollBottom 왜 안되노 ${scrollState.maxValue}")
                scrollState.animateScrollTo(scrollState.maxValue)
            }
            is PetSideEffect.RequestFocus -> {
                focusRequester.requestFocus()
            }
            is PetSideEffect.OpenKeyboard -> {
                keyboardController?.show()
            }
        }
    }
}

@Composable
private fun MemoField(
    modifier: Modifier = Modifier,
    memo: String,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onValueChange: (String) -> Unit,
) {
    ContentTextField(
        modifier = modifier,
        value = memo,
        onValueChange = onValueChange,
        onTextLayout = onTextLayout,
        title = null,
        placeholder = stringResource(id = R.string.pet_memo_placeholder),
        singleLine = false,
        isFilled = false,
        minLines = Int.MAX_VALUE
    )
}