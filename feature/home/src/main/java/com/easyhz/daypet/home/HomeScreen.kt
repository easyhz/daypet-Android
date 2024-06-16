package com.easyhz.daypet.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.design_system.component.bottomSheet.BottomSheet
import com.easyhz.daypet.design_system.component.button.ExpandedFloatingActionButton
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.main.DimScreenProvider
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.design_system.util.fab.FabButtonItem
import com.easyhz.daypet.design_system.util.fab.FabOption
import com.easyhz.daypet.design_system.util.fab.rememberMultiFabState
import com.easyhz.daypet.home.contract.HomeIntent
import com.easyhz.daypet.home.dummy.ARCHIVE_DUMMY
import com.easyhz.daypet.home.dummy.TASK_DUMMY
import com.easyhz.daypet.home.util.getCalendarPadding
import com.easyhz.daypet.home.util.getWeekPageTitle
import com.easyhz.daypet.home.util.rememberFirstVisibleWeekAfterScroll
import com.easyhz.daypet.home.view.HomeTopBar
import com.easyhz.daypet.home.view.calendar.HomeWeekCalendar
import com.easyhz.daypet.home.view.calendar.MonthCalendarBottomSheetContent
import com.easyhz.daypet.home.view.event.ArchiveContent
import com.easyhz.daypet.home.view.event.Event
import com.easyhz.daypet.home.view.event.TaskContent
import com.easyhz.daypet.home.view.event.eventItem
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
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
    val fabState = rememberMultiFabState()

    DayPetScaffold(
        topBar = {
            HomeTopBar(
                title = getWeekPageTitle(visibleWeek),
                onClickTitle = { viewModel.postIntent(HomeIntent.ShowMonthCalendar) }
            )},
        floatingActionButton = {
            ExpandedFloatingActionButton(
                fabState = fabState,
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
                    // TODO: navigate to Item Screen
                },
            )
        }
    ) {
        DimScreenProvider(
            isDim = fabState.value.isExpanded(),
            onDismissRequest = { fabState.value = fabState.value.toggleValue() }
        ) {
            if (uiState.showMonthCalendar) {
                BottomSheet(onDismissRequest = { viewModel.postIntent(HomeIntent.HideMonthCalendar) }) {
                    MonthCalendarBottomSheetContent(
                        currentDate = currentDate,
                        selection = uiState.selection,
                        calendarPadding = calendarPadding
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.padding(it)
            ) {
                item {
                    HomeWeekCalendar(
                        modifier = Modifier
                            .padding(horizontal = calendarPadding)
                            .padding(top = 8.dp, bottom = 12.dp),
                        weekState = state,
                        currentDate = currentDate,
                        selection = uiState.selection,
                        onChangedDate = { clickedDay ->
                            viewModel.postIntent(HomeIntent.ChangeDate(clickedDay))
                        }
                    )
                }
                eventItem(
                    list = ARCHIVE_DUMMY,
                    event = Event.ARCHIVE,
                    modifier = Modifier.screenHorizonPadding()
                ) { archive ->
                    ArchiveContent(
                        archive = archive
                    )
                }
                eventItem(
                    list = TASK_DUMMY,
                    event = Event.TASK,
                    modifier = Modifier.screenHorizonPadding()
                ) { task->
                    TaskContent(
                        task = task
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(76.dp)) // FAB 만큼의 space
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPrev() {
    HomeScreen()
}