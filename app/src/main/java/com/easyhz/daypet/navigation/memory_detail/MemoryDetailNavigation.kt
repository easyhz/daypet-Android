package com.easyhz.daypet.navigation.memory_detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.easyhz.daypet.memory_detail.MemoryDetailScreen

fun NavGraphBuilder.memoryDetailScreen() {
    composable<MemoryDetail> { navBackStackEntry ->
        val args = navBackStackEntry.toRoute<MemoryDetail>()
        MemoryDetailScreen(args.title)
    }
}