package com.easyhz.daypet.memory_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.DayPetIcon
import com.easyhz.daypet.design_system.component.main.IconDefault
import com.easyhz.daypet.design_system.component.textField.BaseTextField
import com.easyhz.daypet.design_system.component.textField.TextFieldType
import com.easyhz.daypet.design_system.theme.Primary

@Composable
fun CommentBottomBar(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BaseTextField(
            modifier = Modifier.weight(1f),
            value = text,
            onValueChange = onValueChange,
            title = null,
            placeholder = stringResource(id = R.string.comment_placeholder),
            textFieldType = TextFieldType.NO_LINE,
            singleLine = true,
            isFilled = false,
        )
        DayPetIcon(
            icon = IconDefault(
                painter = painterResource(id = R.drawable.ic_paperplane),
                size = 28.dp,
                alignment = Alignment.CenterEnd,
                color = Primary
            )
        ) {
            println("click 예아")
            onClick()
        }
    }
}