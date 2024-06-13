package com.easyhz.daypet.home.view.event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.component.main.DayPetIcon
import com.easyhz.daypet.design_system.component.main.IconDefault
import com.easyhz.daypet.design_system.theme.Body1
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubTextColor
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.domain.model.event.Task
import com.easyhz.daypet.design_system.R


@Composable
fun TaskContent(
    task: Task
) {
    val textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None
    val textColor = if (task.isDone) SubTextColor else TextColor
    Box(
        modifier = Modifier
            .height(36.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TaskCircle()
            Text(
                text = task.title,
                style = Body1,
                color = textColor,
                textDecoration = textDecoration
            )
        }
        DayPetIcon(
            modifier = Modifier.align(Alignment.CenterEnd),
            icon = IconDefault(
                painter = painterResource(id = R.drawable.ic_ellipsis),
                size = 18.dp,
                alignment = Alignment.CenterEnd
            )
        )
    }
}

@Composable
private fun TaskCircle() {
    Box(modifier = Modifier
        .size(16.dp)
        .clip(CircleShape)
        .background(Primary))
}

@Preview(showBackground = true, name = "taskContent - isNotDone")
@Composable
private fun TaskContentPrev() {
    val prevTask = Task("초코 낮잠", isDone = false)
    Column {
        TaskContent(prevTask)
    }
}

@Preview(showBackground = true, name = "taskContent - isDone")
@Composable
private fun TaskContentPrevDone() {
    val prevTask = Task("초코 낮잠", isDone = true)
    Column {
        TaskContent(prevTask)
    }
}