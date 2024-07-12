package com.easyhz.daypet.sign.view.pet

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.common.extension.collectInLaunchedEffectWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.bottomSheet.BottomSheet
import com.easyhz.daypet.design_system.component.dialog.DayPetDialog
import com.easyhz.daypet.design_system.component.dialog.DialogButton
import com.easyhz.daypet.design_system.component.loading.LoadingScreenProvider
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.snackBar.DayPetSnackBarHost
import com.easyhz.daypet.design_system.component.topbar.TopBar
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.design_system.util.snackbar.SnackBarType
import com.easyhz.daypet.design_system.util.snackbar.snackBarPadding
import com.easyhz.daypet.design_system.util.topbar.TopBarType
import com.easyhz.daypet.sign.component.BottomSheetItem
import com.easyhz.daypet.sign.component.ImageBottomSheetContent
import com.easyhz.daypet.sign.contract.pet.PetIntent
import com.easyhz.daypet.sign.contract.pet.PetSideEffect
import com.easyhz.daypet.sign.util.PetStep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetScreen(
    viewModel: PetViewModel = hiltViewModel(),
    groupId: String,
    navigateToHome: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    val animatedProgress by animateFloatAsState(
        targetValue = uiState.progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec, label = "screenProgress"
    )
    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { viewModel.postIntent(PetIntent.PickImage(it)) }
        )
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        viewModel.postIntent(PetIntent.InitPetScreen(groupId))
    }

    BackHandler(onBack = {
        viewModel.postIntent(PetIntent.ClickBackButton)
    })
    if (uiState.isShowBottomSheet) {
        BottomSheet(onDismissRequest = { viewModel.postIntent(PetIntent.HideBottomSheet) }) {
            ImageBottomSheetContent(
                items = BottomSheetItem.entries
            ) {
                viewModel.postIntent(PetIntent.ClickBottomSheetItem(it))
            }
        }
    }
    DayPetScaffold(
        topBar = {
            TopBar(
                left = TopBarType.TopBarIconButton(
                    iconId = R.drawable.ic_keyboard_arrow_left,
                    iconAlignment = Alignment.CenterStart,
                    onClick = { viewModel.postIntent(PetIntent.ClickBackButton) }
                ),
                title = TopBarType.TopBarTitle(
                    stringId = R.string.title_pet_profile
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
            LinearProgressIndicator(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .height(2.dp),
                progress = { animatedProgress },
                color = Primary,
                trackColor = MainBackground
            )
            AnimatedContent(
                modifier = Modifier.fillMaxSize().padding(it),
                targetState = uiState.step.currentStep,
                transitionSpec = {
                    if (uiState.step.currentStep.ordinal >= (uiState.step.previousStep?.ordinal
                            ?: 0)
                    ) {
                        (slideInHorizontally { width -> width } + fadeIn()).togetherWith(
                            slideOutHorizontally { width -> -width } + fadeOut())
                    } else {
                        (slideInHorizontally { width -> -width } + fadeIn()).togetherWith(
                            slideOutHorizontally { width -> width } + fadeOut())
                    }.using(SizeTransform(clip = false))
                }, label = "pet"
            ) { targetScreen ->
                when (targetScreen) {
                    PetStep.PROFILE -> PetProfileView()
                    PetStep.INFO -> PetInfoView()
                    PetStep.ATTRIBUTE -> PetAttributeView()
                    PetStep.MEMO -> PetMemoView()
                }
            }
        }
        if (uiState.isOpenPetDialog) {
            DayPetDialog(
                title = stringResource(id = R.string.pet_group_invite_dialog_title),
                content = stringResource(id = R.string.pet_group_invite_dialog_content),
                negativeButton = DialogButton(
                    text = stringResource(id = R.string.pet_add_dialog_negative),
                    contentColor = TextColor,
                    containerColor = ButtonShapeColor,
                    onClick = {
                        viewModel.postIntent(PetIntent.ClickDialogPositiveButton)
                    }
                ),
                positiveButton = DialogButton(
                    text = stringResource(id = R.string.pet_group_invite_positive),
                    contentColor = MainBackground,
                    containerColor = Primary,
                    onClick = {
                        viewModel.postIntent(PetIntent.ClickDialogNegativeButton)
                    }
                ),
            )
        }
    }

    viewModel.sideEffect.collectInLaunchedEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is PetSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }
            is PetSideEffect.NavigateToHome -> {
                navigateToHome(sideEffect.groupId)
            }
            is PetSideEffect.ScrollToBottom -> {
                sideEffect.scrollState.animateScrollTo(sideEffect.scrollState.maxValue)
            }
            is PetSideEffect.RequestFocus -> {
                sideEffect.focusRequester.requestFocus()
            }
            is PetSideEffect.OpenKeyboard -> {
                keyboardController?.show()
            }
            is PetSideEffect.HideKeyboard -> {
                keyboardController?.hide()
            }
            is PetSideEffect.NavigateToGallery -> {
                galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }
}