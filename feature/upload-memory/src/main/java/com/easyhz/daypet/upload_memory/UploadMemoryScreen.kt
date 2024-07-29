package com.easyhz.daypet.upload_memory

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.common.extension.collectInLaunchedEffectWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.dialog.DialogButton
import com.easyhz.daypet.design_system.component.dialog.InfoDialog
import com.easyhz.daypet.design_system.component.loading.LoadingScreenProvider
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.snackBar.DayPetSnackBarHost
import com.easyhz.daypet.design_system.component.topbar.TopBar
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.util.snackbar.SnackBarType
import com.easyhz.daypet.design_system.util.snackbar.snackBarPadding
import com.easyhz.daypet.design_system.util.topbar.TopBarType
import com.easyhz.daypet.upload_memory.contract.UploadIntent
import com.easyhz.daypet.upload_memory.contract.UploadSideEffect
import com.easyhz.daypet.upload_memory.view.bottombar.UploadBottomBar
import com.easyhz.daypet.upload_memory.view.upload.UploadView

@Composable
fun UploadMemoryScreen(
    viewModel: UploadMemoryViewModel = hiltViewModel(),
    navigateToUp: () -> Unit,
    navigateToHome: (String, String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(5),
            onResult = { viewModel.postIntent(UploadIntent.PickImages(it)) }
        )

    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { viewModel.postIntent(UploadIntent.TakePicture(it)) }
        )
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    LaunchedEffect(Unit) {
        viewModel.postIntent(UploadIntent.InitScreen)
    }
    LoadingScreenProvider(
        isLoading = uiState.isLoading
    ) {
        DayPetScaffold(
            modifier = Modifier.noRippleClickable { viewModel.postIntent(UploadIntent.ClearFocus) },
            snackbarHost = {
                DayPetSnackBarHost(
                    hostState = snackBarHostState,
                    modifier = Modifier
                        .snackBarPadding(SnackBarType.Default)
                )
            },
            topBar = {
                TopBar(
                    left = TopBarType.TopBarIconButton(
                        iconId = R.drawable.ic_keyboard_arrow_left,
                        iconAlignment = Alignment.CenterStart,
                        onClick = { viewModel.postIntent(UploadIntent.ClickBackButton) }
                    ),
                    title = TopBarType.TopBarTitle(
                        stringId = R.string.title_upload_memory
                    ),
                    right = TopBarType.TopBarTextButton(
                        stringId = R.string.title_upload_success,
                        onClick = { viewModel.postIntent(UploadIntent.ClickUploadButton) }
                    )
                )
            },
            bottomBar = {
                UploadBottomBar(
                    modifier = Modifier.imePadding(),
                    onClickGallery = { viewModel.postIntent(UploadIntent.ClickToGallery) },
                    onClickCamera = { viewModel.postIntent(UploadIntent.ClickToCamera) }
                )
            }
        ) {
            UploadView(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            )
        }
        if (uiState.isVisibleDialog) {
            InfoDialog(
                title = stringResource(id = R.string.upload_dialog_title),
                content = null,
                negativeButton = null,
                positiveButton = DialogButton(
                    text = stringResource(id = R.string.upload_dialog_button),
                    contentColor = MainBackground,
                    containerColor = Primary,
                    onClick = { viewModel.postIntent(UploadIntent.ClickDialogButton) }
                ),
            ) {

            }
        }
    }

    viewModel.sideEffect.collectInLaunchedEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is UploadSideEffect.NavigateToGallery -> {
                galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
                focusManager.clearFocus()
            }

            is UploadSideEffect.NavigateToCamera -> {
                cameraLauncher.launch(sideEffect.uri)
                focusManager.clearFocus()
            }

            is UploadSideEffect.ScrollToBottom -> {
                scrollState.animateScrollTo(scrollState.maxValue)
            }

            is UploadSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }

            is UploadSideEffect.NavigateToHome -> {
                navigateToHome(sideEffect.groupId, sideEffect.userId)
            }

            is UploadSideEffect.NavigateToUp -> {
                navigateToUp()
            }

            is UploadSideEffect.ClearFocus -> {
                focusManager.clearFocus()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun UploadMemoryScreenPrev() {
    UploadMemoryScreen(navigateToUp = { }) { _, _ ->
    }
}