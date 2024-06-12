package com.easyhz.daypet.home.view.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.theme.ButtonShapeColor

@Composable
fun DayCircle() {
//    if (false) {
//                Image(
//                    modifier = Modifier
//                        .size(36.dp)
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Crop,
//                    painter = painterResource(id = R.drawable.img1),
//                    contentDescription = ""
//                )
//    } else {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(ButtonShapeColor)
        )
//    }
}