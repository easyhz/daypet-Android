package com.easyhz.daypet.home.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarLayoutInfo
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.core.Week
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull

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