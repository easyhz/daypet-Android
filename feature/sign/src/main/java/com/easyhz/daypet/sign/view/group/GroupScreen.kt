package com.easyhz.daypet.sign.view.group

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.common.extension.collectInLaunchedEffectWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.button.MainButton
import com.easyhz.daypet.design_system.component.dialog.DayPetDialog
import com.easyhz.daypet.design_system.component.dialog.DialogButton
import com.easyhz.daypet.design_system.component.loading.LoadingScreenProvider
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.snackBar.DayPetSnackBarHost
import com.easyhz.daypet.design_system.component.textField.BaseTextField
import com.easyhz.daypet.design_system.component.topbar.TopBar
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.Body1
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubBody1
import com.easyhz.daypet.design_system.theme.SubTextColor
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.design_system.theme.Title1
import com.easyhz.daypet.design_system.util.keyboard.keyboardOpenAsState
import com.easyhz.daypet.design_system.util.snackbar.SnackBarType
import com.easyhz.daypet.design_system.util.snackbar.snackBarPadding
import com.easyhz.daypet.design_system.util.topbar.TopBarType
import com.easyhz.daypet.sign.contract.group.GroupIntent
import com.easyhz.daypet.sign.contract.group.GroupSideEffect

@Composable
fun GroupScreen(
    viewModel: GroupViewModel = hiltViewModel(),
    name: String,
    ownerId: String,
    navigateToEnterGroup: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToPet: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    DayPetScaffold(
        topBar = {
            TopBar(
                left = null,
                title = TopBarType.TopBarTitle(
                    stringId = R.string.title_group
                ),
                right = null
            )
        },
        snackbarHost = {
            DayPetSnackBarHost(
                hostState = snackBarHostState,
                modifier = Modifier
                    .snackBarPadding(SnackBarType.ButtonTop)
                    .imePadding()
            )
        }
    ) {
        LoadingScreenProvider(
            isLoading = uiState.isLoading
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .screenHorizonPadding()
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .align(Alignment.TopCenter),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopView(
                        name = name,
                        text = uiState.groupName,
                        onValueChange = { newText -> viewModel.postIntent(GroupIntent.ChangeGroupNameText(newText)) }
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(36.dp)
                            .noRippleClickable {
                                viewModel.postIntent(GroupIntent.ClickEnterGroup)
                            }
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = stringResource(id = R.string.group_enter),
                            style = SubBody1,
                            color = TextColor,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                MainButton(
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .align(Alignment.BottomCenter),
                    enabled = uiState.isButtonEnabled,
                    text = stringResource(id = R.string.group_success),
                    contentColor = MainBackground,
                    containerColor = Primary
                ) {
                    viewModel.postIntent(GroupIntent.ClickCreateGroup(ownerId))
                }
            }
            if (uiState.isOpenPetDialog) {
                DayPetDialog(
                    title = stringResource(id = R.string.pet_add_dialog_title),
                    content = stringResource(id = R.string.pet_add_dialog_content),
                    negativeButton = DialogButton(
                        text = stringResource(id = R.string.pet_add_dialog_negative),
                        contentColor = TextColor,
                        containerColor = ButtonShapeColor,
                        onClick = { viewModel.postIntent(GroupIntent.ClickDialogNegativeButton) }
                    ),
                    positiveButton = DialogButton(
                        text = stringResource(id = R.string.pet_add_dialog_positive),
                        contentColor = MainBackground,
                        containerColor = Primary,
                        onClick = { viewModel.postIntent(GroupIntent.ClickDialogPositiveButton) }
                    ),
                )
            }
        }
    }

    viewModel.sideEffect.collectInLaunchedEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is GroupSideEffect.NavigateToEnterGroup -> { navigateToEnterGroup() }
            is GroupSideEffect.NavigateToHome -> { navigateToHome() }
            is GroupSideEffect.NavigateToPet -> { navigateToPet() }
            is GroupSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }
        }
    }
}

@Composable
private fun TitleView(
    name: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.group_title_text, name),
            textAlign = TextAlign.Center,
            style = Title1
        )
        Text(
            text = stringResource(id = R.string.group_caption_text),
            textAlign = TextAlign.Center,
            style = Body1,
            color = SubTextColor
        )
    }
}

@Composable
private fun TopView(
    modifier: Modifier = Modifier,
    name: String,
    text: String,
    onValueChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val isKeyboardOpen by keyboardOpenAsState()
    val size by animateDpAsState(
        targetValue = if (isKeyboardOpen) 80.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 300,
            easing = androidx.compose.animation.core.FastOutSlowInEasing
        ), label = "imageSize"
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.group_title_text, name),
            textAlign = TextAlign.Center,
            style = Title1
        )
        Image(
            painter = painterResource(id = R.drawable.ic_groups),
            contentDescription = "groups",
            modifier = Modifier
                .size(size)
        )
        BaseTextField(
            modifier = Modifier.focusRequester(focusRequester),
            value = text,
            onValueChange = onValueChange,
            title = stringResource(id = R.string.group_name),
            placeholder = stringResource(id = R.string.group_name_placeholder),
            singleLine = true,
            isFilled = false,
        )
    }

}