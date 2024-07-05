package com.easyhz.daypet.design_system.component.snackBar

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DayPetSnackBarHost(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState,
) {
    SnackbarHost(
        modifier = modifier,
        hostState = hostState
    ) {
        DayPetSnackBar(snackBarData = it)
    }
}