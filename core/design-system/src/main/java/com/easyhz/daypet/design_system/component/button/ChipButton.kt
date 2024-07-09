package com.easyhz.daypet.design_system.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.util.button.ChipState
import com.easyhz.daypet.design_system.util.button.ChipStyle

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Chips(
    modifier: Modifier = Modifier,
    elements: List<ChipState>,
    chipStyle: ChipStyle = ChipStyle(),
    onChipClicked: (String, Boolean, Int) -> Unit,
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        maxItemsInEachRow = 5
    ) {
        elements.forEachIndexed { index, chipState ->
            Chip(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = chipState.label,
                selected = chipState.isSelected.value,
                selectedColor = chipStyle.selectedColor,
                unselectedColor = chipStyle.unselectedColor,
                chipTextStyle = chipStyle.chipTextStyle,
                selectedTextColor = chipStyle.selectedTextColor,
                unselectedTextColor = chipStyle.unselectedTextColor,
                chipModifier = chipStyle.chipModifier,
                onChipClicked = { content, isSelected ->
                    onChipClicked(content, isSelected, index)
                }
            )
        }
    }
}
@Composable
private fun Chip(
    modifier: Modifier = Modifier,
    chipModifier: Modifier,
    text: String,
    selected: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    chipTextStyle: TextStyle,
    selectedTextColor: Color,
    unselectedTextColor: Color,
    onChipClicked: (String, Boolean) -> Unit,
) {
    Box(
        modifier = modifier.clip(RoundedCornerShape(100.dp)).background(
            when {
                selected -> selectedColor
                else -> unselectedColor
            },
        ).padding(vertical = 6.dp, horizontal = 10.dp)
    ) {
        Text(
            text = text,
            color = when {
                selected -> selectedTextColor
                else -> unselectedTextColor
            },
            style = chipTextStyle,
            modifier = chipModifier
                .align(Alignment.Center)
                .noRippleClickable { onChipClicked(text, selected) }
        )
    }
}