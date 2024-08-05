package com.easyhz.daypet.memory_detail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.component.image.ImageCircle
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.memory_detail.contract.Member

@Composable
internal fun MemberView(
    modifier: Modifier = Modifier,
    members: List<Member>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy((-8).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        members.forEachIndexed { index, member ->
            if (index > 3) return
            ImageCircle(
                modifier = Modifier
                    .size(24.dp)
                    .border(1.dp, color = MainBackground, shape = CircleShape),
                imageUrl = member.imageUrl
            )
        }
        if(members.size > 3) {
            Text(text = stringResource(id = R.string.member_over, members.size - 3))
        }
    }
}