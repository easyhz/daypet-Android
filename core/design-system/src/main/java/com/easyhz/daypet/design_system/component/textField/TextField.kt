package com.easyhz.daypet.design_system.component.textField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.theme.Body3

@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    title: String,
    placeholder: String,
    singleLine: Boolean,
    isFilled: Boolean,
    minLines: Int = 1,
    spacing: Dp = 4.dp,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val state = getTextFieldState(text = value, isFilled = isFilled)
    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth(),
        textStyle = Body3,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            TextFieldContainer(
                state = state,
                title = title,
                placeholder = placeholder,
                innerTextField = innerTextField,
                spacing = spacing
            )
        }
    )
}

@Composable
fun ContentTextField(
    modifier: Modifier = Modifier,
    containerModifier: Modifier = Modifier,
    innerFieldModifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    title: String?,
    placeholder: String,
    singleLine: Boolean,
    isFilled: Boolean,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val state = getTextFieldState(text = value, isFilled = isFilled)
    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth(),
        textStyle = Body3,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines,
        onTextLayout = onTextLayout,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            ContentTextFieldContainer(
                modifier = containerModifier,
                innerFieldModifier = innerFieldModifier,
                state = state,
                title = title,
                placeholder = placeholder,
                innerTextField = innerTextField
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun BaseTextFieldPrev() {
    BaseTextField(
        value = "제목을입력해",
        onValueChange = { },
        title = "제목",
        placeholder = "제목을 입력하세요",
        singleLine = true,
        isFilled = false
    )
}

@Preview(showBackground = true)
@Composable
private fun ContentTextFieldPrev() {
    ContentTextField(
        value = "내용을 입력해\n내용을 입력해\n내용을 입력해",
        onValueChange = { },
        title = "내용",
        placeholder = "내용을 입력하세요.",
        singleLine = false,
        isFilled = false
    )
}