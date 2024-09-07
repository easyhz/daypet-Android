package com.easyhz.daypet.design_system.component.picker

import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.Body4
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.util.picker.DatePickerUtil
import com.easyhz.daypet.design_system.util.picker.DatePickerUtil.dateFormatter
import java.time.LocalDate

@Composable
fun DatePickerButton(
    modifier: Modifier = Modifier,
    date: LocalDate = LocalDate.now(),
    onChangeExpanded: (Boolean) -> Unit = { },
    onSelected: (LocalDate) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(dateFormatter.format(date)) }
    LaunchedEffect(key1 = expanded) {
        onChangeExpanded(expanded)
    }
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .height(32.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(ButtonShapeColor)
                .noRippleClickable {
                    expanded = !expanded
                },
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 8.dp),
                text = selectedDate,
                style = Body4
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        DropdownMenu(
            modifier = Modifier.background(MainBackground),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                onSelected(DatePickerUtil.toLocalDate(selectedDate))
            }
        ) {
            AndroidView(
                factory = {
                    val themeContext = ContextThemeWrapper(it, R.style.CalenderViewCustom)
                    CalendarView(themeContext).apply {
                        dateTextAppearance = R.style.CalenderViewDateCustomText
                        weekDayTextAppearance = R.style.CalenderViewWeekCustomText
                        setOnDateChangeListener { _, year, month, dayOfMonth ->
                            selectedDate = DatePickerUtil.toString(year, month + 1, dayOfMonth)
                        }
                    }
                  },
                update = { },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}




@Preview
@Composable
private fun DateButtonPrev() {
    DatePickerButton(date = LocalDate.now()) {

    }
}
