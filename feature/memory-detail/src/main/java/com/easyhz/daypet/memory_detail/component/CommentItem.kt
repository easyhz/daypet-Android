package com.easyhz.daypet.memory_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.image.ImageCircle
import com.easyhz.daypet.design_system.component.main.DayPetIcon
import com.easyhz.daypet.design_system.component.main.IconDefault
import com.easyhz.daypet.design_system.theme.Body1
import com.easyhz.daypet.design_system.theme.Heading5
import com.easyhz.daypet.design_system.theme.SubBody1
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.domain.model.comment.Comment

@Composable
internal fun CommentItem(
    modifier: Modifier = Modifier,
    comment: Comment,
    onClickMore: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageCircle(
                modifier = Modifier
                    .size(32.dp),
                imageUrl = comment.profileImageUrl
            )
            Text(
                modifier = Modifier.weight(1f),
                text = comment.uploaderName,
                style = Heading5
            )
            if (comment.isOwner) {
                DayPetIcon(
                    icon = IconDefault(
                        painter = painterResource(id = R.drawable.ic_ellipsis),
                        size = 16.dp,
                        alignment = Alignment.CenterEnd,
                        color = TextColor
                    )
                ) { onClickMore() }
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 42.dp, bottom = 4.dp),
            text = comment.content,
            style = Body1,
            lineHeight = (16 * 1.3).sp
        )
        Text(modifier = Modifier.padding(start = 42.dp), text = comment.creationTime, style = SubBody1)
    }
}