package com.easyhz.daypet.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.SubTextColor
import com.easyhz.daypet.design_system.util.color.TodoColor
import com.easyhz.daypet.home.util.toColor

@Composable
fun TodoColorView(
    modifier: Modifier = Modifier,
    selectedColor: TodoColor,
    onClick: (TodoColor) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        TodoColor.entries.forEach {
            Box(
                modifier = Modifier
                    .sizeIn(minWidth = 32.dp, minHeight = 32.dp)
                    .noRippleClickable { onClick(it) }, contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(it.code.toColor())
                        .then(
                            if (selectedColor == it) Modifier.border(
                                width = 1.5.dp,
                                color = SubTextColor,
                                shape = CircleShape
                            ) else Modifier
                        )
                )
            }
        }
    }
}

@Preview
@Composable
private fun TodoColorViewPrev() {
    TodoColorView(selectedColor = TodoColor.LIGHT_YELLOW) {}
}