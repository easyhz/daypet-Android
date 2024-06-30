package com.easyhz.daypet.upload_memory

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.easyhz.daypet.common.extension.collectInLaunchedEffectWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.topbar.TopBar
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

    DayPetScaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.title_upload_memory),
                navigateToBefore = { /* TODO: Navigate TO Back */ },
                navigateToNext = { /* TODO: Navigate TO Next */ }
            )
        },
        bottomBar = {
            UploadBottomBar(
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
            }

            is UploadSideEffect.NavigateToCamera -> {
                cameraLauncher.launch(sideEffect.uri)
            }

            is UploadSideEffect.ScrollToBottom -> {
                scrollState.animateScrollTo(scrollState.maxValue)
            }
            is UploadSideEffect.ChangeFocus -> {
                //FIXME: 고치기
//                handleChangeFocus(sideEffect.focusType, scrollState)
            }
        }
    }
}

//private suspend fun handleChangeFocus(focusType: FocusType, scrollState: ScrollState) {
//    when(focusType) {
//        FocusType.NONE -> { println("none")
//        }
//        FocusType.TITLE -> { println("title") }
//        FocusType.CONTENT -> {
////            println("maxVlaue > ${scrollState.maxValue}")
////            scrollState.animateScrollTo(scrollState.maxValue)
//        }
//    }
//
//}


@Preview(showBackground = true)
@Composable
private fun UploadMemoryScreenPrev() {
    UploadMemoryScreen()
}