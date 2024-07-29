package com.easyhz.daypet.navigation.upload_memory

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhz.daypet.upload_memory.UploadMemoryScreen

fun NavGraphBuilder.uploadMemoryScreen(
    navigateToUp: () -> Unit,
    navigateToHome: (String, String) -> Unit
) {
    composable<UploadMemory> {
        UploadMemoryScreen(
            navigateToUp = navigateToUp,
            navigateToHome = navigateToHome,
        )
    }
}