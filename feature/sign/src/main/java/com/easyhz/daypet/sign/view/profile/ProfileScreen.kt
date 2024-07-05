package com.easyhz.daypet.sign.view.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.common.extension.collectInLaunchedEffectWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.button.MainButton
import com.easyhz.daypet.design_system.component.image.ProfileImage
import com.easyhz.daypet.design_system.component.image.ProfileImageType
import com.easyhz.daypet.design_system.component.loading.LoadingScreenProvider
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.snackBar.DayPetSnackBarHost
import com.easyhz.daypet.design_system.component.textField.BaseTextField
import com.easyhz.daypet.design_system.component.topbar.TopBar
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubBody2
import com.easyhz.daypet.design_system.util.snackbar.SnackBarType
import com.easyhz.daypet.design_system.util.snackbar.snackBarPadding
import com.easyhz.daypet.design_system.util.topbar.TopBarType
import com.easyhz.daypet.sign.AuthViewModel
import com.easyhz.daypet.sign.contract.auth.AuthIntent
import com.easyhz.daypet.sign.contract.auth.AuthSideEffect

@Composable
fun ProfileScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToGroup: (String, String) -> Unit,
    navigateToBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    DayPetScaffold(
        topBar = {
            TopBar(
                left = TopBarType.TopBarIconButton(
                    iconId = R.drawable.ic_keyboard_arrow_left,
                    iconAlignment = Alignment.CenterStart,
                    onClick = navigateToBack
                ),
                title = TopBarType.TopBarTitle(
                    stringId = R.string.title_profile
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
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ProfileImage(
                        type = ProfileImageType.User,
                        modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                    }

                    BaseTextField(
                        value = uiState.name,
                        onValueChange = { newText -> viewModel.postIntent(AuthIntent.ChangeNameText(newText)) },
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
                    enabled = uiState.isProfileButtonEnabled,
                    text = stringResource(id = R.string.profile_name_next),
                    contentColor = MainBackground,
                    containerColor = Primary
                ) {
                    viewModel.postIntent(AuthIntent.ClickProfileNextButton)
                }
            }
        }
    }
    viewModel.sideEffect.collectInLaunchedEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is AuthSideEffect.NavigateToGroup -> { navigateToGroup(uiState.name, uiState.uid) }
            is AuthSideEffect.ClearFocus -> { focusManager.clearFocus() }
            is AuthSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }
        }
    }
}