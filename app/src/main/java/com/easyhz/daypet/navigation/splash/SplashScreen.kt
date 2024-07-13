package com.easyhz.daypet.navigation.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhz.daypet.navigation.sign.navigateToGroup
import com.easyhz.daypet.navigation.sign.navigateToHome
import com.easyhz.daypet.navigation.sign.screen.Login
import com.easyhz.daypet.splash.SplashScreen

internal fun NavGraphBuilder.splashScreen(
    navController: NavController,
) {
    composable<Splash> {
        SplashScreen(
            navigateToHome = navController::navigateToHome,
            navigateToGroup = navController::navigateToGroup,
            navigateToLogin = navController::navigateToLogin
        )
    }
}

internal fun NavController.navigateToLogin() {
    navigate(Login) {
        popUpTo(this@navigateToLogin.graph.id) { inclusive = true }
    }
}