package com.easyhz.daypet.memory_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.DayPetIcon
import com.easyhz.daypet.design_system.component.main.IconDefault
import com.easyhz.daypet.design_system.extension.borderTop
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubHeading1

@Composable
internal fun DetailBottomBar(
    modifier: Modifier = Modifier,
    commentCount: Int,
    onClickComment: () -> Unit,
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .borderTop(color = ButtonShapeColor, width = 1.dp)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = commentCount.toString(), style = SubHeading1)
            DayPetIcon(
                icon = IconDefault(
                    painter = painterResource(id = R.drawable.ic_bubble),
                    size = 24.dp,
                    alignment = Alignment.Center,
                    color = Primary
                )
            ) { onClickComment() }
        }

    }
}