package com.easyhz.daypet.design_system.component.image

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubTextColor

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageCircle(
    modifier: Modifier = Modifier,
    imageUrl: String,
    alpha: Float = 1f
) {
    GlideImage(
        modifier = modifier
            .clip(CircleShape),
        model = imageUrl,
        contentDescription = imageUrl,
        loading = placeholder(ColorPainter(SubTextColor)),
        failure = placeholder(ColorPainter(Primary)),
        contentScale = ContentScale.Crop,
        transition = CrossFade,
        alpha = alpha
    )
}
