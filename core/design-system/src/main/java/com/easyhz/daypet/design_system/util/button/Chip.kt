package com.easyhz.daypet.design_system.util.button

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubHeading1
import com.easyhz.daypet.design_system.theme.TextColor

data class ChipState(
    var label: String,
    val isSelected: MutableState<Boolean>
)

data class ChipStyle(
    val selectedColor: Color = Primary,
    val unselectedColor: Color = ButtonShapeColor,
    val chipTextStyle: TextStyle = SubHeading1,
    val selectedTextColor: Color = MainBackground,
    val unselectedTextColor: Color = TextColor,
    val chipModifier: Modifier = Modifier,
)

fun List<String>.toChipState(): List<ChipState> =
    this.map { ChipState(label = it, isSelected = mutableStateOf(false)) }