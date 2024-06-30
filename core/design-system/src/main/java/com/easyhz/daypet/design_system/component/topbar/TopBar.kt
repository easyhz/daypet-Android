package com.easyhz.daypet.design_system.component.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.Heading5
import com.easyhz.daypet.design_system.util.topbar.TopBarType
import com.easyhz.daypet.design_system.util.topbar.content

@Composable
fun TopBar(
    left: TopBarType?,
    title: TopBarType.TopBarTitle?,
    right: TopBarType?,
) {
    Box(
        modifier = Modifier
            .height(48.dp)
            .screenHorizonPadding()
            .fillMaxWidth()
    ) {
        left.content(
            modifier = Modifier.align(Alignment.CenterStart)
        )
        title.content(
            modifier = Modifier.align(Alignment.Center)
        )
        right.content(
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}


@Composable
internal fun TopBarTextButton(
    modifier: Modifier = Modifier, text: String, onClick: () -> Unit
) {
    Box(modifier = modifier
        .size(32.dp)
        .noRippleClickable { onClick() }) {
        Text(
            text = text, style = Heading5, modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopBarPrev() {
    TopBar(
        TopBarType.TopBarIconButton(
            iconId = R.drawable.ic_keyboard_arrow_left,
            iconAlignment = Alignment.CenterStart,
            onClick = { }
        ),
        title = TopBarType.TopBarTitle(
            stringId = R.string.title_upload_memory
        ),
        right = TopBarType.TopBarTextButton(stringId = R.string.title_upload_success,
            onClick = { }
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun TextButtonPrev() {
    TopBarTextButton(text = "완료") { }
}