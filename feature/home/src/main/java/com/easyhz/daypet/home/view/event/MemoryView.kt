package com.easyhz.daypet.home.view.event

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateNext
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.component.image.ImageCircle
import com.easyhz.daypet.design_system.theme.Body1
import com.easyhz.daypet.design_system.theme.SubBody2
import com.easyhz.daypet.design_system.theme.SubTextColor
import com.easyhz.daypet.domain.model.memory.Memory

@Composable
internal fun MemoryContent(
    memory: Memory,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .height(72.dp)
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ImageCircle(
                modifier = Modifier.size(56.dp),
                imageUrl = memory.imageUrl
            )
            MemoryInfo(memory)
        }
        Icon(
            modifier = Modifier.size(24.dp).align(Alignment.CenterEnd),
            imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
            contentDescription = null
        )
    }
}

@Composable
private fun MemoryInfo(
    memory: Memory
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = memory.title,
            style = Body1,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Outlined.AccessTime, contentDescription = null,
                tint = SubTextColor
            )
            Text(
                text = memory.time,
                style = SubBody2,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MemoryContentPrev() {
    val memory = Memory(documentId = "", title = "뽀삐 초코 바닷가 간 날", imageUrl = "https://picsum.photos/id/237/200/300", time = "10:07")
    MemoryContent(memory) {}
}