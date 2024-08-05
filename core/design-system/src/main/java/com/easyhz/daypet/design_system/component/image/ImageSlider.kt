package com.easyhz.daypet.design_system.component.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.Primary

@Composable
fun ImageSlider(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    itemCount: Int,
    content: @Composable (index: Int) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        HorizontalPager(state = pagerState) {page ->
            content(page)
        }
        if(itemCount > 1) {
            DotsIndicator(
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp),
                total = itemCount,
                selected = pagerState.currentPage,
                dotSize = 8.dp
            )
        }
    }
}


@Composable
private fun Dot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
private fun DotsIndicator(
    modifier: Modifier = Modifier,
    total: Int,
    selected: Int,
    selectedColor : Color = Primary,
    unSelectedColor: Color = ButtonShapeColor,
    dotSize: Dp,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(total) { index ->
            Dot(
                size = dotSize,
                color = if(index == selected) selectedColor else unSelectedColor
            )
        }
    }
}