package com.easyhz.daypet.home.view.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.theme.Body2
import com.easyhz.daypet.design_system.theme.Heading3
import com.easyhz.daypet.design_system.theme.ImageFadeBackgroundColor
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubHeading1
import com.easyhz.daypet.design_system.theme.SubTextColor
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.domain.model.home.Thumbnail
import com.easyhz.daypet.home.util.dateFormatter
import com.easyhz.daypet.home.util.displayText
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.LocalDate

@Stable
@Composable
internal fun CalendarHeader() {
    val daysOfWeek = remember { daysOfWeek() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                text = dayOfWeek.displayText(),
                style = SubHeading1,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
internal fun CalendarTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = Heading3
        )
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
            contentDescription = "NavigateNext"
        )
    }
}

sealed class DayType {
    data class Calendar(val calendarDay: CalendarDay) : DayType()
    data class Week(val weekDay: WeekDay) : DayType()
}
@Composable
internal fun Day(
    modifier: Modifier = Modifier,
    dayType: DayType,
    thumbnail: Thumbnail,
    isSelected: Boolean,
    isCurrentDate: Boolean,
    onClick: (LocalDate) -> Unit
) {
    val date = when (dayType) {
        is DayType.Calendar -> dayType.calendarDay.date
        is DayType.Week -> dayType.weekDay.date
    }
    val selectionColor = if(isSelected) {
        ImageFadeBackgroundColor
    } else {
        MainBackground
    }
    val currentDateColor = when {
        dayType is DayType.Calendar -> when (dayType.calendarDay.position) {
            DayPosition.MonthDate -> if (isCurrentDate) Primary else TextColor
            DayPosition.InDate, DayPosition.OutDate -> SubTextColor
        }
        isCurrentDate -> Primary
        else -> TextColor
    }
    val url = thumbnail.thumbnailUrls[date.toString()]
    val isRangeDate = when {
        dayType is DayType.Calendar && dayType.calendarDay.position != DayPosition.MonthDate -> false
        else -> true
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(selectionColor)
            .clickable { onClick(date) }
            .padding(vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            DayCircle(
                thumbnailUrl = url,
                isRangeDate = isRangeDate
            )
            Text(
                text = dateFormatter.format(date),
                style = Body2,
                textAlign = TextAlign.Center,
                color = currentDateColor,
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }
}