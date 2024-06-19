package com.easyhz.daypet.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhz.daypet.home.HomeScreen
import com.easyhz.daypet.navigation.memory_detail.MemoryDetail

internal fun NavGraphBuilder.homeScreen(
    navigateToMemoryDetail: (String) -> Unit
) {
    composable<Home> {
        HomeScreen(
            navigateToMemoryDetail = navigateToMemoryDetail
        )
    }
}

internal fun NavController.navigateToMemoryDetail(title: String) {
    navigate(MemoryDetail(title = title))
}