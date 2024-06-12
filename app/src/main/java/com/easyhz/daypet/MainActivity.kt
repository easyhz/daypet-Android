package com.easyhz.daypet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.easyhz.daypet.design_system.theme.DayPetTheme
import com.easyhz.daypet.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DayPetTheme {
                HomeScreen()
            }
        }
    }
}
