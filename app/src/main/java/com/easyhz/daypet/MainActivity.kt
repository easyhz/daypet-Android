package com.easyhz.daypet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.easyhz.daypet.design_system.theme.DayPetTheme
import com.easyhz.daypet.navigation.home.Home
import com.easyhz.daypet.navigation.home.homeScreen
import com.easyhz.daypet.navigation.home.navigateToMemoryDetail
import com.easyhz.daypet.navigation.memory_detail.memoryDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DayPetTheme {
                NavHost(
                    navController = navController,
                    startDestination = Home
                ) {
                    homeScreen(
                        navigateToMemoryDetail = navController::navigateToMemoryDetail
                    )
                    memoryDetailScreen()
                }
            }
        }
    }
}
