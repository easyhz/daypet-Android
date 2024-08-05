package com.easyhz.daypet

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.easyhz.daypet.navigation.home.homeScreen
import com.easyhz.daypet.navigation.home.navigateToMemoryDetail
import com.easyhz.daypet.navigation.home.navigateToUploadMemory
import com.easyhz.daypet.navigation.memory_detail.memoryDetailScreen
import com.easyhz.daypet.navigation.sign.navigateToHome
import com.easyhz.daypet.navigation.sign.signScreen
import com.easyhz.daypet.navigation.splash.Splash
import com.easyhz.daypet.navigation.splash.splashScreen
import com.easyhz.daypet.navigation.upload_memory.uploadMemoryScreen

const val DURATION = 500

@Composable
fun DayPetApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Splash,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) }
    ) {
        splashScreen(
            navController = navController
        )
        homeScreen(
            navigateToMemoryDetail = navController::navigateToMemoryDetail,
            navigateToUploadMemory = navController::navigateToUploadMemory
        )
        memoryDetailScreen(
            navigateToUp = navController::navigateUp
        )
        uploadMemoryScreen(
            navigateToUp = navController::navigateUp,
            navigateToHome = navController::navigateToHome
        )
        signScreen(
            navController = navController,
        )
    }
}