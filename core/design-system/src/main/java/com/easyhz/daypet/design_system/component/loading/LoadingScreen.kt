package com.easyhz.daypet.design_system.component.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.Dim
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.MainBackground

@Composable
fun LoadingScreenProvider(
    isLoading: Boolean = false,
    content: @Composable () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLoading
    )
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .noRippleClickable { focusManager.clearFocus() }
    ) {
        content()
        Dim(
            isDim = isLoading,
            dimColor = MainBackground.copy(alpha = 0.5f),
            statusBarColor = MainBackground.copy(alpha = 0.5f),
            navigationBarColor = MainBackground.copy(alpha = 0.5f),
            onDismissRequest = { }
        )
        if (isLoading) {
            LottieAnimation(
                modifier = Modifier.size((screenWidthDp / 2).dp).align(Alignment.Center),
                composition = composition,
                progress = { progress },
            )
        }
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreenProvider(isLoading = true) {

    }
}