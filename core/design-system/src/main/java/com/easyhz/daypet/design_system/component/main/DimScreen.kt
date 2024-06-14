package com.easyhz.daypet.design_system.component.main

import android.app.Activity
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.MainBackground

/**
 * Dim 효과 주는 컴포저블
 * - 무언가 활성화 됐을때 강조 효과를 주기 위해 사용한다. (ex. 플로팅 액션 버튼의 스피드 다이얼)
 *
 * @param isDim 활성화 여부
 * @param dimColor 어두워지는 색
 * @param statusBarColor statusBar 색 (null 이면 적용 X)
 * @param navigationBarColor navigationBar 색 (null 이면 적용 X)
 * @param onDismiss 배경을 눌렀을 때 이벤트
 * @param content UI
 */
@Composable
fun DimScreenProvider(
    isDim: Boolean,
    dimColor: Color = Color.Black.copy(0.25f),
    statusBarColor: Color? = MainBackground,
    navigationBarColor: Color? = MainBackground,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()
        Dim(
            isDim = isDim,
            dimColor = dimColor,
            statusBarColor = statusBarColor,
            navigationBarColor = navigationBarColor,
            onDismiss = onDismiss
        )
    }
}

@Composable
private fun Dim(
    isDim: Boolean,
    dimColor: Color,
    statusBarColor: Color?,
    navigationBarColor: Color?,
    onDismiss: () -> Unit,
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            updateWindowColors(
                view = view,
                isDim = isDim,
                dimColor = dimColor,
                statusBarColor = statusBarColor,
                navigationBarColor = navigationBarColor
            )
        }
    }
    if (isDim) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(dimColor)
                .noRippleClickable { onDismiss() }
        )
    }
}

/**
 * statusBar, navigationBar 을 [dimColor] 와 통일.
 *
 * @param view 현재 뷰
 * @param isDim 활성화 여부
 * @param dimColor 어두워지는 색
 * @param statusBarColor statusBar 색 (null 이면 적용 X)
 * @param navigationBarColor navigationBar 색 (null 이면 적용 X)
 */
private fun updateWindowColors(
    view: View,
    isDim: Boolean,
    dimColor: Color,
    statusBarColor: Color? = null,
    navigationBarColor: Color? = null
) {
    val window = (view.context as Activity).window
    statusBarColor?.let {
        window.statusBarColor = if (isDim) dimColor.toArgb() else it.toArgb()
    }

    navigationBarColor?.let {
        window.navigationBarColor = if (isDim) dimColor.toArgb() else it.toArgb()
    }
}