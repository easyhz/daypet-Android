package com.easyhz.daypet.design_system.component.textField

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubBody2
import com.easyhz.daypet.design_system.theme.SubBody3
import com.easyhz.daypet.design_system.theme.SubTextColor

enum class TextFieldType(
    val hasDivider: Boolean,
    val verticalAlignment: Alignment.Vertical,
    val placeholderAlignment: Alignment,
) {
    ONE_LINE(hasDivider = true, verticalAlignment = Alignment.Bottom, placeholderAlignment = Alignment.BottomStart),
    CONTENT(hasDivider = false, verticalAlignment = Alignment.Top, placeholderAlignment = Alignment.TopStart)
}

@Composable
internal fun TextFieldContainer(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    title: String?,
    placeholder: String,
    spacing: Dp = 4.dp,
    innerTextField: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        title?.let {
            TextFieldContainerTitle(
                title = title,
            )
        }
        TextFieldContainerContent(
            modifier = Modifier.height(25.dp),
            type = TextFieldType.ONE_LINE,
            state = state,
            placeholder = placeholder,
            innerTextField = innerTextField,
        )
    }
}

@Composable
internal fun ContentTextFieldContainer(
    modifier: Modifier = Modifier,
    innerFieldModifier:Modifier = Modifier,
    state: TextFieldState,
    title: String?,
    placeholder: String,
    spacing: Dp = 12.dp,
    innerTextField: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        title?.let {
            TextFieldContainerTitle(
                title = title,
            )
        }
        TextFieldContainerContent(
            modifier = innerFieldModifier,
            type = TextFieldType.CONTENT,
            state = state,
            placeholder = placeholder,
            innerTextField = innerTextField,
        )
    }
}

@Composable
private fun TextFieldContainerTitle(
    title: String,
) {
    Text(
        text = title,
        style = SubBody2,
        color = Primary
    )
}

@Composable
private fun TextFieldContainerContent(
    modifier: Modifier = Modifier,
    type: TextFieldType,
    state: TextFieldState,
    placeholder: String,
    innerTextField: @Composable () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = type.verticalAlignment
    ) {
        Box(modifier = Modifier.weight(1f)) {
            innerTextField()
            if (state == TextFieldState.Default) {
                Text(
                    modifier = Modifier.fillMaxWidth().align(type.placeholderAlignment),
                    text = placeholder,
                    style = SubBody3
                )
            }
        }
    }
    if (type.hasDivider) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp)
                .height(1.dp),
            color = SubTextColor
        )
    }
}

@Preview
@Composable
private fun TextFieldContainerPrev() {
    val state = getTextFieldState("", false)
    TextFieldContainer(state = state, title = "제목", placeholder = "제목을 입력하세요.") {

    }
}

@Preview
@Composable
private fun ContentTextFieldContainerPrev() {
    val state = getTextFieldState("", false)
    ContentTextFieldContainer(state = state, title = "내용", placeholder = "내용을 입력하세요.") {

    }

}