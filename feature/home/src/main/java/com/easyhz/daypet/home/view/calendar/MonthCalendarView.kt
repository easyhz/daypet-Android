package com.easyhz.daypet.home.view.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.common.R
import com.easyhz.daypet.design_system.component.button.MainButton
import com.easyhz.daypet.domain.model.home.Thumbnail
import com.easyhz.daypet.home.HomeViewModel
import com.easyhz.daypet.home.contract.HomeIntent
import com.easyhz.daypet.home.util.collectMonthChange
import com.easyhz.daypet.home.util.displayText
import com.easyhz.daypet.home.util.rememberFirstMostVisibleMonth
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.OutDateStyle
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import java.time.DayOfWeek
import java.time.LocalDate

const val RANGE_MONTH = 500L
@Composable
internal fun MonthCalendarBottomSheetContent(
    viewModel: HomeViewModel = hiltViewModel(),
    currentDate: LocalDate,
    selection: LocalDate,
    firstDayOfWeek: DayOfWeek = firstDayOfWeekFromLocale(),
    outDateStyle: OutDateStyle = OutDateStyle.EndOfGrid,
    calendarPadding: Dp = 10.dp,
    onClickSelectButton: (LocalDate) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val startMonth = remember { currentDate.yearMonth.minusMonths(RANGE_MONTH) }
    val endMonth = remember { currentDate.yearMonth.plusMonths(RANGE_MONTH) }
    val monthState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = selection.yearMonth,
        firstDayOfWeek = firstDayOfWeek,
        outDateStyle = outDateStyle,
    )
    val title = rememberFirstMostVisibleMonth(state = monthState)

    LaunchedEffect(key1 = Unit) {
        viewModel.postIntent(HomeIntent.ChangeDateOnMonth(selection))
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CalendarTitle(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 24.dp, bottom = 12.dp),
            title = title.yearMonth.displayText()
        )
        HomeMonthCalendar(
            modifier = Modifier.padding(horizontal = calendarPadding),
            monthState = monthState,
            selection = uiState.monthSelection,
            currentDate = currentDate,
            thumbnail = uiState.thumbnail,
            onChangeDate = { clickedDay ->
                viewModel.postIntent(HomeIntent.ChangeDateOnMonth(clickedDay))
            }
        )
        MainButton(
            text = stringResource(id = R.string.button_select_success),
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            onClickSelectButton(uiState.monthSelection)
        }
    }

    collectMonthChange(state = monthState) { start, last ->
        viewModel.postIntent(HomeIntent.ScrollMonth(start, last))
    }
}


@Composable
private fun HomeMonthCalendar(
    modifier: Modifier = Modifier,
    monthState: CalendarState,
    currentDate: LocalDate,
    selection: LocalDate,
    thumbnail: Thumbnail,
    onChangeDate: (LocalDate) -> Unit,
) {
    HorizontalCalendar(
        state = monthState,
        monthHeader = { CalendarHeader() },
        dayContent = {day ->
            Day(
                dayType = DayType.Calendar(day),
                isSelected = selection == day.date,
                isCurrentDate = currentDate == day.date,
                thumbnail = thumbnail,
                modifier = Modifier.padding(bottom = 12.dp),
            ) { clickedDay ->
                onChangeDate(clickedDay)
            }
        },
        modifier = modifier
    )
}