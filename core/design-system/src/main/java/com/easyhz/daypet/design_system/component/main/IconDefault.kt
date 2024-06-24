package com.easyhz.daypet.design_system.component.main

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.theme.TextColor


@Immutable
data class IconDefault(
    val imageVector: ImageVector? = null,
    val painter: Painter? = null,
    val size: Dp = 32.dp,
    val alignment: Alignment = Alignment.Center,
    val color: Color = TextColor
)