package com.easyhz.daypet.home.view.event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.DayPetIcon
import com.easyhz.daypet.design_system.component.main.IconDefault
import com.easyhz.daypet.design_system.theme.Body1
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubTextColor
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.domain.model.todo.Todo
import com.easyhz.daypet.home.util.toColor


@Composable
internal fun TodoContent(
    todo: Todo
) {
    val textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None
    val textColor = if (todo.isDone) SubTextColor else TextColor
    Row(
        modifier = Modifier
            .heightIn(min = 36.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        TodoCircle(
            modifier = Modifier.align(Alignment.CenterVertically),
            color = todo.todoColor.toColor()
        )
        Box(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .weight(1f)
                .heightIn(min = 16.dp)
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = todo.title,
                style = Body1.merge(
                    lineHeight = 16.sp,
                    lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.None
                )
                ),
                color = textColor,
                textDecoration = textDecoration,
            )
        }

        DayPetIcon(
            icon = IconDefault(
                painter = painterResource(id = R.drawable.ic_ellipsis),
                size = 18.dp,
                alignment = Alignment.CenterEnd
            )
        )
    }
}

@Composable
private fun TodoCircle(
    modifier: Modifier = Modifier,
    color: Color = Primary
) {
    Box(modifier = modifier
        .size(16.dp)
        .clip(CircleShape)
        .background(color)
    )
}

@Preview(showBackground = true, name = "todoContent - isNotDone")
@Composable
private fun TodoContentPrev() {
    val prevTodo = Todo("초코 낮잠", isDone = false, todoColor = "BFA7F0")
    Column {
        TodoContent(prevTodo)
    }
}

@Preview(showBackground = true, name = "todoContent - isDone")
@Composable
private fun TodoContentPrevDone() {
    val prevTodo = Todo("초코 낮잠", isDone = true, todoColor = "BFA7F0")
    Column {
        TodoContent(prevTodo)
    }
}