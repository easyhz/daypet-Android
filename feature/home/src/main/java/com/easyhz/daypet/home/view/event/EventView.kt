package com.easyhz.daypet.home.view.event

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.Heading4
import com.easyhz.daypet.design_system.theme.SubBody1
import com.easyhz.daypet.design_system.R

enum class Event(
    @StringRes val titleId: Int,
    val icon: ImageVector
) {
    MEMORY(
        titleId = R.string.event_memory,
        icon = Icons.Outlined.CalendarMonth
    ),
    TODO(
        titleId = R.string.event_todo,
        icon = Icons.Outlined.CheckCircle
    )
}

fun<T> LazyListScope.eventItem(
    modifier: Modifier = Modifier,
    list: List<T>,
    event: Event,
    content: @Composable (T) -> Unit
) {
    item {
        Spacer(modifier = Modifier.height(12.dp))
    }
    item {
        EventTitle(modifier = modifier, event = event)
    }
    if (list.isEmpty()) {
        item {
            Text(
                modifier = Modifier
                    .height(72.dp)
                    .padding(top = 12.dp)
                    .screenHorizonPadding(),
                text = "${stringResource(id = event.titleId)}${stringResource(id = R.string.need_add)}",
                style = SubBody1,
            )
        }
    } else {
        items(list) {type ->
            Box(modifier = modifier.fillMaxWidth()) {
                content(type)
            }
        }
    }

}

@Composable
private fun EventTitle(
    modifier: Modifier = Modifier,
    event: Event
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(id = event.titleId),
            style = Heading4
        )
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = event.icon,
            contentDescription = stringResource(id = event.titleId)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun EventTitlePrev() {
    EventTitle(event = Event.MEMORY)
}

