package com.easyhz.daypet.design_system.component.textField

import androidx.compose.ui.graphics.Color
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubTextColor


enum class TextFieldIcons(
    internal val resourceId: Int,
    internal val color: (state: TextFieldState) -> Color
) {
    SEND(
        resourceId = R.drawable.ic_paperplane,
        color = { state ->
            when (state) {
                is TextFieldState.Default -> SubTextColor
                is TextFieldState.Active -> Primary
                is TextFieldState.Filled -> Primary
            }
        }
    )
}