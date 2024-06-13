package com.easyhz.daypet.design_system.util.fab

import androidx.compose.ui.graphics.Color
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary

// di 로 하지 않은 이유는 배보다 배꼽이 더 커서 (불필요한 오버헤드)

/**
 * 플로팅 액션 버튼의 옵션
 */
interface FabOption {
    val iconColor: Color
    val backgroundColor: Color
}

/**
 * [FabOption] 인터페이스의의 구현부
 *
 * @property iconColor 서브 메뉴 아이콘 색 [Color]
 * @property backgroundColor 서브 메뉴 배경색 [Color]
 */
private class FabOptionImpl(
    override val iconColor: Color,
    override val backgroundColor: Color
) : FabOption

/**
 * [FabOption]의 인스턴스 생성
 *
 * @param iconColor 서브 메뉴 아이콘 색 [Color]
 * @param backgroundColor 서브 메뉴 배경색 [Color]
 *
 * @return 주어진 옵션으로 생성된 [FabOption]의 인스턴스
 */
fun FabOption(
    iconColor: Color = MainBackground,
    backgroundColor: Color = Primary,
): FabOption = FabOptionImpl(iconColor, backgroundColor)