package com.easyhz.daypet.design_system.component.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dns
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.extension.noRippleClickable


@Composable
fun DayPetIcon(
    modifier: Modifier = Modifier,
    icon: IconDefault,
    onClick: () -> Unit = {}
) {
    Box(modifier = modifier.size(icon.size.coerceAtLeast(32.dp)).noRippleClickable { onClick() }) {
        when {
            icon.imageVector != null -> {
                Icon(
                    imageVector = icon.imageVector,
                    contentDescription = null,
                    modifier = Modifier
                        .align(icon.alignment)
                        .size(icon.size)
                )
            }
            icon.painter != null -> {
                Icon(
                    painter = icon.painter,
                    contentDescription = null,
                    modifier = Modifier
                        .align(icon.alignment)
                        .size(icon.size)
                )
            }
        }
    }
}