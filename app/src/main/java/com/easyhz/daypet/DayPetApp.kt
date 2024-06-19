package com.easyhz.daypet

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.easyhz.daypet.navigation.home.Home
import com.easyhz.daypet.navigation.home.homeScreen
import com.easyhz.daypet.navigation.home.navigateToMemoryDetail
import com.easyhz.daypet.navigation.home.navigateToUploadMemory
import com.easyhz.daypet.navigation.memory_detail.memoryDetailScreen
import com.easyhz.daypet.navigation.upload_memory.uploadMemoryScreen

@Composable
fun DayPetApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        homeScreen(
            navigateToMemoryDetail = navController::navigateToMemoryDetail,
            navigateToUploadMemory = navController::navigateToUploadMemory
        )
        memoryDetailScreen()
        uploadMemoryScreen()
    }
}