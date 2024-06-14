package com.easyhz.daypet.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.design_system.component.button.ExpandedFloatingActionButton
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.design_system.util.fab.FabButtonItem
import com.easyhz.daypet.design_system.util.fab.FabOption
import com.easyhz.daypet.home.contract.HomeIntent
import com.easyhz.daypet.home.dummy.ARCHIVE_DUMMY
import com.easyhz.daypet.home.dummy.TASK_DUMMY
import com.easyhz.daypet.home.util.getCalendarPadding
import com.easyhz.daypet.home.util.getWeekPageTitle
import com.easyhz.daypet.home.util.rememberFirstVisibleWeekAfterScroll
import com.easyhz.daypet.home.view.HomeTopBar
import com.easyhz.daypet.home.view.calendar.HomeWeekCalendar
import com.easyhz.daypet.home.view.event.EventView
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import java.time.LocalDate

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
        topBar = { HomeTopBar(title = getWeekPageTitle(visibleWeek)) },
        floatingActionButton = {
            ExpandedFloatingActionButton(
                items = listOf(
                    FabButtonItem(
                        imageVector = Icons.Outlined.CalendarMonth,
                        label = stringResource(id = R.string.event_archive)
                    ),
                    FabButtonItem(
                        imageVector = Icons.Outlined.CheckCircleOutline,
                        label = stringResource(id = R.string.event_task)
                    ),
                ),
                mainMenu = FabButtonItem(
                    imageVector = Icons.Outlined.Add,
                    label = stringResource(id = R.string.menu_add),
                    fapOption = FabOption()
                ),
                subMenuOption = FabOption(
                    iconColor = TextColor,
                    backgroundColor = ButtonShapeColor
                ),
                onFabItemClicked = {
                    Toast.makeText(context, it.label, Toast.LENGTH_SHORT).show()
                },
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            HomeWeekCalendar(
                modifier = Modifier
                    .padding(horizontal = calendarPadding, vertical = 8.dp),
                weekState = state,
                currentDate = currentDate,
                selection = uiState.selection,
                onChangedDate = { clickedDay ->
                    viewModel.postIntent(HomeIntent.ChangeDate(clickedDay))
                }
            )
            EventView(
                modifier = Modifier.screenHorizonPadding(),
                archiveList = ARCHIVE_DUMMY, taskList = TASK_DUMMY
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPrev() {
    HomeScreen()
}