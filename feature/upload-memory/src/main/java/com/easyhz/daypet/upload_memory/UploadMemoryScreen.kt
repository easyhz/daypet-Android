package com.easyhz.daypet.upload_memory

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.easyhz.daypet.common.extension.collectInLaunchedEffectWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.topbar.TopBar
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.util.topbar.TopBarType
import com.easyhz.daypet.upload_memory.contract.UploadIntent
import com.easyhz.daypet.upload_memory.contract.UploadSideEffect
import com.easyhz.daypet.upload_memory.view.bottombar.UploadBottomBar
import com.easyhz.daypet.upload_memory.view.upload.UploadView

@Composable
fun UploadMemoryScreen(
    viewModel: UploadMemoryViewModel = hiltViewModel()
) {
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

    DayPetScaffold(
        modifier = Modifier.noRippleClickable { focusManager.clearFocus() },
        topBar = {
            TopBar(
                left = TopBarType.TopBarIconButton(
                    iconId = R.drawable.ic_keyboard_arrow_left,
                    iconAlignment = Alignment.CenterStart,
                    onClick = { /* TODO: Navigate To Back */}
                ),
                title = TopBarType.TopBarTitle(
                    stringId = R.string.title_upload_memory
                ),
                right = TopBarType.TopBarTextButton(
                    stringId = R.string.title_upload_success,
                    onClick = { /* TODO: Navigate TO Next */}
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
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun UploadMemoryScreenPrev() {
    UploadMemoryScreen()
}