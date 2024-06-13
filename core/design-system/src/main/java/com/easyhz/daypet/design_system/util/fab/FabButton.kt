package com.easyhz.daypet.design_system.util.fab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.ui.graphics.vector.ImageVector

// di 로 하지 않은 이유는 배보다 배꼽이 더 커서 (불필요한 오버헤드)

/**
 * 확장 가능한 기본 플로팅 액션 버튼
 */
interface FabButton {
    val imageVector: ImageVector
    val iconRotate: Float?
    val description: String
    val fapOption: FabOption
}

/**
 * [FabButton] 인터페이스의 구현부
 *
 * @property imageVector 플로팅 액션 버튼에 표시 되는 [ImageVector]
 * @property iconRotate 아이콘 회전 각도 (null 이면 회전 X)
 * @property description 이름
 * @property fapOption 플로팅 액션 버튼의 옵션 (색상)
 */
private class FabButtonImpl(
    override val imageVector: ImageVector,
    override val iconRotate: Float?,
    override val description: String,
    override val fapOption: FabOption
) : FabButton

/**
 * [FabButton]의 인스턴스 생성
 *
 * @param imageVector 플로팅 액션 버튼에 표시 되는 [ImageVector]
 * @param iconRotate 아이콘 회전 각도 (null 이면 회전 X)
 * @param label 이름
 * @property fapOption 플로팅 액션 버튼의 옵션 (색상)
 *
 * @return 주어진 옵션으로 생성된 [FabButton]의 인스턴스
 */
fun FabButton(imageVector: ImageVector = Icons.Outlined.Add, iconRotate: Float = 45f, label: String = "", fapOption: FabOption): FabButton =
    FabButtonImpl(imageVector, iconRotate, label, fapOption)