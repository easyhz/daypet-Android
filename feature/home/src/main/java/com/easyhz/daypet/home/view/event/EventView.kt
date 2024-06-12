package com.easyhz.daypet.home.view.event

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.theme.Heading2
import com.easyhz.daypet.design_system.theme.SubBody1
import com.easyhz.daypet.domain.model.event.Archive
import com.easyhz.daypet.domain.model.event.Task
import com.easyhz.daypet.home.R

enum class Event(
    @StringRes val titleId: Int,
    val icon: ImageVector
) {
    ARCHIVE(
        titleId = R.string.event_archive,
        icon = Icons.Outlined.CalendarMonth
    ),
    TASK(
        titleId = R.string.event_task,
        icon = Icons.Outlined.CheckCircle
    )
}

@Composable
fun EventView(
    modifier: Modifier = Modifier,
    archiveList: List<Archive>,
    taskList: List<Task>
) {
    LazyColumn(modifier = modifier) {
        eventItem(list = archiveList, event = Event.ARCHIVE) { archive ->
            ArchiveContent(archive = archive)
        }
        eventItem(list = taskList, event = Event.TASK) { task->
            TaskContent(task = task)
        }

    }
}

@Composable
private fun EventTitle(
    event: Event
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(id = event.titleId),
            style = Heading2
        )
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = event.icon,
            contentDescription = stringResource(id = event.titleId)
        )
    }
}

private fun<T> LazyListScope.eventItem(
    list: List<T>,
    event: Event,
    content: @Composable (T) -> Unit
) {
    item {
        Spacer(modifier = Modifier.height(16.dp))
    }
    item {
        EventTitle(event = event)
    }
    if (list.isEmpty()) {
        item {
            Text(
                modifier = Modifier.height(72.dp).padding(top = 10.dp),
                text = "${stringResource(id = event.titleId)}을 추가해 주세요.",
                style = SubBody1,
            )
        }
    } else {
        items(list) {type ->
            content(type)
        }
    }
    item {
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Preview(showBackground = true)
@Composable
private fun EventTitlePrev() {
    EventTitle(Event.ARCHIVE)
}

@Preview(showBackground = true)
@Composable
private fun EventViewPrev() {
    EventView(archiveList = emptyList(), taskList = emptyList())
}
