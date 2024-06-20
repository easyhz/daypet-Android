package com.easyhz.daypet.home.view.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.component.image.ImageCircle
import com.easyhz.daypet.design_system.theme.ButtonShapeColor

@Composable
internal fun DayCircle(
    thumbnailUrl: String?,
    isRangeDate: Boolean,
) {
    if (thumbnailUrl != null) {
        val alpha = if (isRangeDate) 1f else 0.5f
        ImageCircle(
            modifier = Modifier.size(36.dp),
            imageUrl = thumbnailUrl,
            alpha = alpha
        )
    } else {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(ButtonShapeColor)
        )
    }
}