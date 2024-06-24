package com.easyhz.daypet.design_system.component.picker

import android.view.LayoutInflater
import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.easyhz.daypet.design_system.util.picker.TimePickerUtil
import com.easyhz.daypet.design_system.util.picker.TimePickerUtil.timeFormatter
import java.time.LocalTime

@Composable
fun TimePickerButton(
    time: LocalTime = LocalTime.now(),
    onSelected: (LocalTime) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf(timeFormatter.format(time)) }

    Column {
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
                text = selectedTime,
                style = Body4
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        DropdownMenu(
            modifier = Modifier.background(MainBackground),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                onSelected(TimePickerUtil.toLocalTime(selectedTime))
            }
        ) {
            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { context ->
                    val view = LayoutInflater.from(context).inflate(R.layout.time_picker, null)
                    val datePicker = view.findViewById<TimePicker>(R.id.timePicker)
                    datePicker.apply {
                        setOnTimeChangedListener { _, hourOfDay, minute ->
                            selectedTime = TimePickerUtil.toString(hourOfDay, minute)
                        }
                    }
                }
            )
        }
    }
}




@Preview
@Composable
private fun DateButtonPrev() {
    DatePickerButton() {

    }
}
