package com.easyhz.daypet.sign.view.pet

import androidx.compose.animation.AnimatedContent
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.snackBar.DayPetSnackBarHost
import com.easyhz.daypet.design_system.component.topbar.TopBar
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.util.snackbar.SnackBarType
import com.easyhz.daypet.design_system.util.snackbar.snackBarPadding
import com.easyhz.daypet.design_system.util.topbar.TopBarType
import com.easyhz.daypet.sign.util.PetStep

// FIXME : 뒤로가기 물리 버튼, 뒤로가기 버튼 눌럿을때 전 단계 돌아 가기
@Composable
fun PetScreen(
    viewModel: PetViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val animatedProgress by animateFloatAsState(
        targetValue = uiState.progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec, label = "screenProgress"
    )

    DayPetScaffold(
        topBar = {
            TopBar(
                left = TopBarType.TopBarIconButton(
                    iconId = R.drawable.ic_keyboard_arrow_left,
                    iconAlignment = Alignment.CenterStart,
                    onClick = { }
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
            targetState = uiState.currentStep,
            transitionSpec = {
                (slideInHorizontally { width -> width } + fadeIn()).togetherWith(
                    slideOutHorizontally { width -> -width } + fadeOut())
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
}