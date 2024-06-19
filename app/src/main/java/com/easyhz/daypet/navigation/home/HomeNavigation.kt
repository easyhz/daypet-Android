package com.easyhz.daypet.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhz.daypet.home.HomeScreen
import com.easyhz.daypet.navigation.memory_detail.MemoryDetail
import com.easyhz.daypet.navigation.upload_memory.UploadMemory

/**
 * [HomeScreen] 과 관련된 navigation 을 정의 하는 파일
 *
 * [NavController] 의 확장 함수는 현재 파일의 주체인 [HomeScreen]이 출발지 일 때 정의 하는 것을 원칙으로 한다.
 */


internal fun NavGraphBuilder.homeScreen(
    navigateToMemoryDetail: (String) -> Unit,
    navigateToUploadMemory: () -> Unit
) {
    composable<Home> {
        HomeScreen(
            navigateToMemoryDetail = navigateToMemoryDetail,
            navigateToUploadMemory = navigateToUploadMemory
        )
    }
}

internal fun NavController.navigateToMemoryDetail(title: String) {
    navigate(MemoryDetail(title = title))
}

internal fun NavController.navigateToUploadMemory() {
    navigate(UploadMemory)
}