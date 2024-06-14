package com.easyhz.daypet.design_system.util.fab

import androidx.compose.ui.graphics.Color
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary

/**
 * [FabOption]
 *
 * @property iconColor 서브 메뉴 아이콘 색 [Color]
 * @property backgroundColor 서브 메뉴 배경색 [Color]
 */
data class FabOption(
    val iconColor: Color = MainBackground,
    val backgroundColor: Color = Primary
)