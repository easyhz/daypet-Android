package com.easyhz.daypet.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.easyhz.daypet.common.extension.collectInLaunchedEffectWithLifecycle
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.splash.contract.SplashSideEffect

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: (String) -> Unit,
    navigateToGroup: (String, String) -> Unit,
    navigateToLogin: () -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Primary,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(
                    id = com.easyhz.daypet.design_system.R.drawable.ic_daypet_logo,
                ),
                contentDescription = "logo",
            )
        }
    }

    viewModel.sideEffect.collectInLaunchedEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is SplashSideEffect.NavigateToLogin -> { navigateToLogin() }
            is SplashSideEffect.NavigateToGroup -> { navigateToGroup(sideEffect.name, sideEffect.ownerId) }
            is SplashSideEffect.NavigateToHome -> { navigateToHome(sideEffect.groupId) }
        }
    }
}