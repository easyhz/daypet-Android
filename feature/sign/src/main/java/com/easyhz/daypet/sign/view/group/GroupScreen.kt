package com.easyhz.daypet.sign.view.group

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.button.MainButton
import com.easyhz.daypet.design_system.component.loading.LoadingScreenProvider
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.textField.BaseTextField
import com.easyhz.daypet.design_system.component.topbar.TopBar
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.Body1
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubBody1
import com.easyhz.daypet.design_system.theme.SubBody2
import com.easyhz.daypet.design_system.theme.SubTextColor
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.design_system.theme.Title1
import com.easyhz.daypet.design_system.util.keyboard.keyboardOpenAsState
import com.easyhz.daypet.design_system.util.topbar.TopBarType
import com.easyhz.daypet.sign.contract.group.GroupIntent

@Composable
fun GroupScreen(
    viewModel: GroupViewModel = hiltViewModel(),
    name: String,
    navigateToBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isKeyboardOpen by keyboardOpenAsState()
    DayPetScaffold(
        topBar = {
            TopBar(
                left = null,
                title = TopBarType.TopBarTitle(
                    stringId = R.string.title_group
                ),
                right = null
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
                    verticalArrangement = Arrangement.spacedBy(36.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TitleView(name)
                    Image(
                        painter = painterResource(id = R.drawable.ic_groups),
                        contentDescription = "groups",
                        modifier = Modifier
                            .size(100.dp)
                    )
                    TextFieldView(
                        text = uiState.name,
                        onValueChange = { newText -> viewModel.postIntent(GroupIntent.ChangeNameText(newText)) }
                    )
                }

                Column(
                    modifier = Modifier.align(Alignment.BottomCenter),
                ) {
                    MainButton(
                        modifier = Modifier.padding(bottom = 12.dp),
                        enabled = uiState.isButtonEnabled,
                        text = stringResource(id = R.string.group_success),
                        contentColor = MainBackground,
                        containerColor = Primary
                    ) {
                        viewModel.postIntent(GroupIntent.ClickCreateGroup)
                    }
                    AnimatedVisibility(
                        visible = !isKeyboardOpen,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(36.dp)
                                .padding(bottom = 12.dp)
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
                }
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
private fun TextFieldView(
    text: String,
    onValueChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BaseTextField(
            value = text,
            onValueChange = onValueChange,
            title = stringResource(id = R.string.group_name),
            placeholder = stringResource(id = R.string.group_name_placeholder),
            singleLine = true,
            isFilled = false,
        )
        Text(
            text = stringResource(id = R.string.group_name_caption),
            style = SubBody2
        )
    }
}