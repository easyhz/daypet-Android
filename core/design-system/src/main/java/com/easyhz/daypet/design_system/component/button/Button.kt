package com.easyhz.daypet.design_system.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.Heading2
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubTextColor

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    contentColor: Color = MainBackground,
    containerColor: Color = Primary,
    onClick: () -> Unit
) {
    val onClickInvoke: () -> Unit = remember(enabled, onClick) {
        if (enabled) onClick else { { } }
    }
    val backgroundColor = remember(enabled, containerColor) {
        if (enabled) containerColor else SubTextColor
    }
    Box(
        modifier = modifier
            .imePadding()
            .height(52.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .noRippleClickable { onClickInvoke() },
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = Heading2,
            color = contentColor
        )
    }
}


@Preview
@Composable
private fun MainButtonPrev() {
    MainButton(
        text = "버튼",
        contentColor = Primary,
        containerColor = ButtonShapeColor,
        enabled = false
    ) { }
}