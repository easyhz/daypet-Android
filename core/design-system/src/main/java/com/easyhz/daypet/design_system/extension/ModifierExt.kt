package com.easyhz.daypet.design_system.extension

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.screenHorizonPadding(): Modifier = padding(horizontal = 20.dp)

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}


/**
 * 그림자 효과 [Modifier] 확장 함수
 *
 * @param shadowColor 그림자 색
 * @param shadowBlurRadius 블러의 정도
 * @param shadowOffsetY y축으로 얼마나 갈건지
 *
 * @return [Modifier]
 */
fun Modifier.shadowEffect(
    shadowColor: Color,
    shadowBlurRadius: Dp,
    shadowOffsetY: Dp = 0.dp
): Modifier = this.then(
    Modifier.drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            paint.asFrameworkPaint().apply {
                color = shadowColor.toArgb()
                maskFilter = BlurMaskFilter(
                    shadowBlurRadius.toPx(),
                    BlurMaskFilter.Blur.NORMAL
                )
            }
            canvas.drawOval(
                left = 0f,
                top = shadowOffsetY.toPx(),
                right = size.width,
                bottom = size.height + shadowOffsetY.toPx(),
                paint = paint
            )
        }
    }
)

/**
 * top 에 border 주는 확장 함수
 *
 * @param color border color
 * @param width border width
 *
 * @return [Modifier]
 */
fun Modifier.borderTop(color: Color, width: Dp): Modifier = this.drawBehind {
    val strokeWidth = width.toPx()
    drawLine(
        color = color,
        start = androidx.compose.ui.geometry.Offset(0f, strokeWidth / 2),
        end = androidx.compose.ui.geometry.Offset(size.width, strokeWidth / 2),
        strokeWidth = strokeWidth
    )
}