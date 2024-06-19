package com.easyhz.daypet.memory_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MemoryDetailScreen(
    title: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "MemoryDetailScreen - $title")
    }
}