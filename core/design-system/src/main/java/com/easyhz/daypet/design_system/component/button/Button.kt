package com.easyhz.daypet.design_system.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.Heading2
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.NoRippleTheme
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
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            modifier = modifier
                .height(52.dp)
                .fillMaxWidth(),
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = SubTextColor,
                disabledContentColor = ButtonShapeColor,
            ),
        ) {
            Text(
                text = text,
                style = Heading2,
            )
        }
    }
}


@Preview
@Composable
private fun MainButtonPrev() {
    MainButton(
        text = "버튼",
        contentColor = Primary,
        containerColor = ButtonShapeColor,
        enabled = true
    ) { }
}