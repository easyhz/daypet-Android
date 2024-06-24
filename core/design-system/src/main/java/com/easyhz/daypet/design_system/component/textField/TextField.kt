package com.easyhz.daypet.design_system.component.textField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
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