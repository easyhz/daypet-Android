package com.easyhz.daypet.design_system.util.fab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.ui.graphics.vector.ImageVector

/**
* [FabButtonItem]
*
* @property imageVector 플로팅 액션 버튼에 표시 되는 [ImageVector]
* @property iconRotate 아이콘 회전 각도 (null 이면 회전 X)
* @property label 이름
* @property fapOption 플로팅 액션 버튼의 옵션 (색상)
* @property type 플로팅 액션 버튼 타입 : 인터페이스를 상속받아 구현한다.
*/
data class FabButtonItem(
    val imageVector: ImageVector = Icons.Outlined.Add,
    val label: String = "",
    val iconRotate: Float? = 45f,
    val fapOption: FabOption = FabOption(),
    val type: FabItemType,
)

interface FabItemType