package com.easyhz.daypet.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.home.util.getCalendarPadding
import com.easyhz.daypet.home.util.getWeekPageTitle
import com.easyhz.daypet.home.util.rememberFirstVisibleWeekAfterScroll
import com.easyhz.daypet.home.view.HomeTopBar
import com.easyhz.daypet.home.view.calendar.HomeWeekCalendar
import com.easyhz.daypet.home.view.event.EventView
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import java.time.LocalDate

@Composable
fun HomeScreen() {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val calendarPadding = getCalendarPadding(20, screenWidth).dp
    val currentDate = remember { LocalDate.now() }
    val startDate = remember { currentDate.minusDays(500) }
    val endDate = remember { currentDate.plusDays(500) }
    val state = rememberWeekCalendarState(
        startDate = startDate,
        endDate = endDate,
        firstVisibleWeekDate = currentDate,
    )
    val visibleWeek = rememberFirstVisibleWeekAfterScroll(state)
    DayPetScaffold(
        topBar = { HomeTopBar(title = getWeekPageTitle(visibleWeek)) }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            HomeWeekCalendar(
                modifier = Modifier
                    .padding(horizontal = calendarPadding, vertical = 8.dp),
                weekState = state,
                currentDate = currentDate
            )
            EventView(
                modifier = Modifier.screenHorizonPadding(),
                archiveList = emptyList(), taskList = emptyList()
            )
        }
    }
}