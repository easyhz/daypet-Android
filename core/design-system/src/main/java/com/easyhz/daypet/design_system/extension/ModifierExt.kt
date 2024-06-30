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
 * 그림자 효과 [Modifier] 확장 함수 - 버튼 기준
 *
 * @param shadowColor 그림자 색
 * @param borderRadius 버튼 radius
 * @param blurRadius 블러 radius
 * @param offsetY y 오프셋
 * @param offsetX x 오프셋
 * @param spread 얼마나 퍼질건지
 * @param modifier Modifier
 *
 * @return [Modifier]
 */
fun Modifier.buttonShadowEffect(
    shadowColor: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0f.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + spreadPixel)
            val bottomPixel = (this.size.height + spreadPixel)

            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = shadowColor.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
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