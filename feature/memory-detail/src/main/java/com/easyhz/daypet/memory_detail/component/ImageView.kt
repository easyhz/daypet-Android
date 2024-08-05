package com.easyhz.daypet.memory_detail.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.easyhz.daypet.design_system.component.image.ImageSlider
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubTextColor

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
internal fun ImageView(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    itemCount: Int,
    imageList: List<String>
) {
    ImageSlider(modifier = modifier, pagerState = pagerState, itemCount = itemCount) { index ->
        GlideImage(
            modifier = Modifier.fillMaxWidth().aspectRatio(1f),
            model = imageList[index],
            contentDescription = imageList[index],
            loading = placeholder(ColorPainter(SubTextColor)),
            failure = placeholder(ColorPainter(Primary)),
            contentScale = ContentScale.Crop,
            transition = CrossFade,
        )
    }
}