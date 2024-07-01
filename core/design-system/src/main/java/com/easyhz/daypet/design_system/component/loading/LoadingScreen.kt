package com.easyhz.daypet.design_system.component.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.Primary

@Composable
fun LoadingScreenProvider(
    isLoading: Boolean = false,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        content()
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp).align(Alignment.Center),
                color = Primary,
                trackColor = ButtonShapeColor,
                strokeWidth = 6.dp
            )
        }
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreenProvider(isLoading = true) {

    }
}