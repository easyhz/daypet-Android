package com.easyhz.daypet.design_system.component.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.DayPetIcon
import com.easyhz.daypet.design_system.component.main.IconDefault
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.Heading4
import com.easyhz.daypet.design_system.theme.Heading5

@Composable
fun TopBar(
    title: String,
    navigateToBefore: () -> Unit,
    navigateToNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(48.dp)
            .screenHorizonPadding()
            .fillMaxWidth()
    ) {
        DayPetIcon(
            modifier = Modifier.align(Alignment.CenterStart),
            icon = IconDefault(
                painter = painterResource(id = R.drawable.ic_keyboard_arrow_left),
                size = 32.dp,
                alignment = Alignment.CenterStart
            ),
            onClick = navigateToBefore
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "새 기억",
            style = Heading4
        )
        TopBarTextButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = "완료",
            onClick = navigateToNext
        )
    }
}


@Composable
private fun TopBarTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .noRippleClickable { onClick() }
    ) {
        Text(
            text = text,
            style = Heading5,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopBarPrev() {
    TopBar("새 기억", { }, { })
}

@Preview(showBackground = true)
@Composable
private fun TextButtonPrev() {
    TopBarTextButton(text = "완료") { }
}