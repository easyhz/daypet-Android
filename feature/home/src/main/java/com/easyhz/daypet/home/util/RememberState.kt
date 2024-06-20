package com.easyhz.daypet.home.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import com.kizitonwose.calendar.compose.CalendarLayoutInfo
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarLayoutInfo
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.Week
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import java.time.LocalDate

/**
 * 스크롤 감지하고 해당 주 반환
 *
 * @param state WeekCalendar 의 상태인 [WeekCalendarState]
 * @param viewportPercent 넘어오는 정도 default : 50f
 *
 * @return 해당 주 [Week]
 */
@Composable
internal fun rememberFirstVisibleWeekAfterScroll(
    state: WeekCalendarState,
    viewportPercent: Float = 50f
): Week {
    val visibleWeek = remember(state) { mutableStateOf(state.firstVisibleWeek) }
    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.firstMostVisibleWeek(viewportPercent) }
            .distinctUntilChanged()
            .filterNotNull()
            .collect { week ->
                visibleWeek.value = week
            }
    }
    return visibleWeek.value
}

/**
 * 스크롤 감지하고 주가 바뀌었는지 수집.
 *
 * @param state WeekCalendar 의 상태인 [WeekCalendarState]
 * @param viewportPercent 넘어오는 정도 default : 10f
 * @param collect 보이는 주의 첫번째 날짜와 마지막 날짜를 collect
 *
 */
@SuppressLint("ComposableNaming")
@Composable
internal fun collectWeekChange(
    state: WeekCalendarState,
    viewportPercent: Float =  10f,
    collect: (LocalDate, LocalDate) -> Unit
) {
    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.firstMostVisibleWeek(viewportPercent) }
            .distinctUntilChanged()
            .filterNotNull()
            .collect { week ->
                val firstDate = week.days.first().date
                val lastDate = week.days.last().date
                if(firstDate.month != lastDate.month) {
                    collect(firstDate, lastDate)
                }
            }
    }
}

private fun WeekCalendarLayoutInfo.firstMostVisibleWeek(viewportPercent: Float = 50f): Week? {
    return if (visibleWeeksInfo.isEmpty()) {
        null
    } else {
        val viewportSize = (viewportEndOffset + viewportStartOffset) * viewportPercent / 100f
        visibleWeeksInfo.firstOrNull { itemInfo ->
            if (itemInfo.offset < 0) {
                itemInfo.offset + itemInfo.size >= viewportSize
            } else {
                itemInfo.size - itemInfo.offset >= viewportSize
            }
        }?.week
    }
}

/**
 * 스크롤 감지하고 해당 월 반환
 *
 * @param state HorizontalCalendar 의 상태인 [CalendarState]
 * @param viewportPercent 넘어오는 정도 default : 50f
 *
 * @return 해당 주 [Week]
 */
@Composable
internal fun rememberFirstMostVisibleMonth(
    state: CalendarState,
    viewportPercent: Float = 50f,
): CalendarMonth {
    val visibleMonth = remember(state) { mutableStateOf(state.firstVisibleMonth) }
    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.firstMostVisibleMonth(viewportPercent) }
            .distinctUntilChanged()
            .filterNotNull()
            .collect { month -> visibleMonth.value = month }
    }
    return visibleMonth.value
}


/**
 * 스크롤 감지하고 월이 바뀌었는지 수집.
 *
 * @param state CalendarState 의 상태인 [CalendarState]
 * @param viewportPercent 넘어오는 정도 default : 10f
 * @param collect 보이는 월의 첫번째 날짜와 마지막 날짜를 collect
 *
 */
@SuppressLint("ComposableNaming")
@Composable
internal fun collectMonthChange(
    state: CalendarState,
    viewportPercent: Float =  10f,
    collect: (LocalDate, LocalDate) -> Unit
) {
    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.firstMostVisibleMonth(viewportPercent) }
            .distinctUntilChanged()
            .filterNotNull()
            .collect { month ->
                val firstDate = month.weekDays.first().first().date
                val lastDate = month.weekDays.last().last().date
                if(firstDate.month != lastDate.month) {
                    collect(firstDate, lastDate)
                }
            }
    }
}


private fun CalendarLayoutInfo.firstMostVisibleMonth(viewportPercent: Float = 50f): CalendarMonth? {
    return if (visibleMonthsInfo.isEmpty()) {
        null
    } else {
        val viewportSize = (viewportEndOffset + viewportStartOffset) * viewportPercent / 100f
        visibleMonthsInfo.firstOrNull { itemInfo ->
            if (itemInfo.offset < 0) {
                itemInfo.offset + itemInfo.size >= viewportSize
            } else {
                itemInfo.size - itemInfo.offset >= viewportSize
            }
        }?.month
    }
}
