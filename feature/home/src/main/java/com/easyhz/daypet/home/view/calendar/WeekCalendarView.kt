package com.easyhz.daypet.home.view.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.Body2
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubHeading1
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.home.util.dateFormatter
import com.easyhz.daypet.home.util.displayText
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun HomeWeekCalendar(
    modifier: Modifier = Modifier,
    weekState: WeekCalendarState,
    currentDate: LocalDate
) {
    var selection by rememberSaveable { mutableStateOf(currentDate) }
    val daysOfWeek = remember { daysOfWeek() }
    WeekCalendar(
        state = weekState,
        weekHeader = { CalendarHeader(daysOfWeek = daysOfWeek) },
        dayContent = {day ->
            Day(
                date = day.date,
                isSelected = selection == day.date,
                isCurrentDate = currentDate == day.date
            ) { clickedDay ->
                if (selection != clickedDay) {
                    selection = clickedDay
                }
            }
        },
        modifier = modifier
    )
}


@Composable
private fun Day(
    date: LocalDate,
    isSelected: Boolean,
    isCurrentDate: Boolean,
    onClick: (LocalDate) -> Unit
) {
    val selectionColor = if (isSelected) TextColor else MainBackground
    val currentDateColor = if (isCurrentDate) Primary else TextColor

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClickable {
                onClick(date)
                println("day.position.name ${date.dayOfWeek.name} ,, ")
            },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            DayCircle()
            Text(
                text = dateFormatter.format(date),
                style = Body2,
                textAlign = TextAlign.Center,
                color = currentDateColor,
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Box(
                modifier = Modifier
                    .width(18.dp)
                    .height(1.dp)
                    .background(selectionColor)
            )
        }
    }
}

@Stable
@Composable
private fun CalendarHeader(daysOfWeek: List<DayOfWeek>) {
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

@Preview
@Composable
private fun DayPreview() {

}