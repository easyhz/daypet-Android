package com.easyhz.daypet.sign

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.common.extension.collectInLaunchedEffectWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.loading.LoadingScreenProvider
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.snackBar.DayPetSnackBarHost
import com.easyhz.daypet.design_system.extension.buttonShadowEffect
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.AppleContainerColor
import com.easyhz.daypet.design_system.theme.AppleContentColor
import com.easyhz.daypet.design_system.theme.Caption1
import com.easyhz.daypet.design_system.theme.GoogleContainerColor
import com.easyhz.daypet.design_system.theme.GoogleContentColor
import com.easyhz.daypet.design_system.theme.Heading5
import com.easyhz.daypet.design_system.theme.SubBody1
import com.easyhz.daypet.design_system.theme.SubTitle
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.design_system.theme.Title
import com.easyhz.daypet.design_system.util.snackbar.SnackBarType
import com.easyhz.daypet.design_system.util.snackbar.snackBarPadding
import com.easyhz.daypet.sign.contract.auth.AuthIntent
import com.easyhz.daypet.sign.contract.auth.AuthSideEffect

/**
 * TODO : Login Screen
 * TODO : Kakao Button
 * TODO : Profile Screen
 * TODO : Group Screen
 *
 */
@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToHome: (String, String) -> Unit,
    navigateToProfile: () -> Unit,
    navigateToGroup: (String, String) -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    DayPetScaffold(
        snackbarHost = {
            DayPetSnackBarHost(
                hostState = snackBarHostState,
                modifier = Modifier
                    .snackBarPadding(SnackBarType.Default)
            )
        }
    ) {
        LoadingScreenProvider(
            isLoading = uiState.isLoading
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 160.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_icon),
                        contentDescription = "appIcon",
                        modifier = Modifier
                            .size(72.dp)
                            .buttonShadowEffect(
                                shadowColor = Color.Black.copy(0.25f),
                                borderRadius = 14.dp,
                                blurRadius = 2.dp,
                                offsetY = 4.dp
                            )
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.app_name_ko),
                            style = Title
                        )
                        Text(
                            text = stringResource(id = R.string.app_description),
                            style = SubTitle
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .screenHorizonPadding(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    LoginView(
                        onClickSocial = object : SocialLoginType.OnItemClickListener {
                            override fun onClickGoogle() {
                                viewModel.postIntent(AuthIntent.ClickSignInWithGoogle(context))
                            }

                            override fun onClickApple() {

                            }
                        },
                        onClickEmail = { println("email") }
                    )

                    Text(
                        modifier = Modifier.padding(bottom = 20.dp),
                        text = stringResource(id = R.string.login_info),
                        style = Caption1
                    )
                }
            }
        }
    }

    viewModel.sideEffect.collectInLaunchedEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is AuthSideEffect.NavigateToProfile -> {
                navigateToProfile()
            }
            is AuthSideEffect.NavigateToGroup -> {
                navigateToGroup(sideEffect.name, sideEffect.uid)
            }
            is AuthSideEffect.NavigateToHome -> {
                navigateToHome(sideEffect.groupId, sideEffect.uid)
            }
            is AuthSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }

        }
    }
}

enum class SocialLoginType(
    @StringRes val stringId: Int,
    @DrawableRes val iconId: Int,
    val containerColor: Color,
    val contentColor: Color,
) {
    APPLE(
        stringId = R.string.social_login_apple,
        iconId = R.drawable.ic_apple,
        containerColor = AppleContainerColor,
        contentColor = AppleContentColor
    ),
    GOOGLE(
        stringId = R.string.social_login_google,
        iconId = R.drawable.ic_google,
        containerColor = GoogleContainerColor,
        contentColor = GoogleContentColor,
    ), ;

    interface OnItemClickListener {
        fun onClickGoogle()
        fun onClickApple()
    }

}

@Composable
private fun LoginView(
    modifier: Modifier = Modifier,
    onClickSocial: SocialLoginType.OnItemClickListener,
    onClickEmail: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SocialLoginButton(
            socialLoginType = SocialLoginType.GOOGLE,
            onClick = onClickSocial::onClickGoogle
        )
        Spacer(modifier = Modifier.height(12.dp))
        SocialLoginButton(
            socialLoginType = SocialLoginType.APPLE,
            onClick = onClickSocial::onClickApple
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp)
                .noRippleClickable { onClickEmail() }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.login_email),
                style = SubBody1,
                color = TextColor,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Composable
private fun SocialLoginButton(
    modifier: Modifier = Modifier,
    socialLoginType: SocialLoginType,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .buttonShadowEffect(
                shadowColor = Color.Black.copy(0.25f),
                borderRadius = 8.dp,
                blurRadius = 2.dp,
                offsetY = 1.dp
            )
            .height(56.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(socialLoginType.containerColor)
            .noRippleClickable { onClick() },
    ) {
        Image(
            painter = painterResource(id = socialLoginType.iconId),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = socialLoginType.stringId),
            style = Heading5,
            color = socialLoginType.contentColor
        )
    }
}

@Preview
@Composable
private fun LoginButtonPrev() {
    SocialLoginButton(socialLoginType = SocialLoginType.GOOGLE) {

    }
}