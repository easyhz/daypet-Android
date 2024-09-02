package com.easyhz.daypet.design_system.util.topbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.component.main.DayPetIcon
import com.easyhz.daypet.design_system.component.main.IconDefault
import com.easyhz.daypet.design_system.component.topbar.TopBarTextButton
import com.easyhz.daypet.design_system.theme.Heading4

sealed class TopBarType {
    data class TopBarTitle(
        @StringRes val stringId: Int
    ): TopBarType()

    data class TopBarTitleString(
        val string: String
    ):TopBarType()

    data class TopBarTextButton(
        @StringRes val stringId: Int,
        val onClick: () -> Unit
    ) : TopBarType()

    data class TopBarIconButton(
        @DrawableRes val iconId: Int,
        val iconAlignment: Alignment,
        val onClick: () -> Unit
    ) : TopBarType()
}

@Composable
fun TopBarType?.content(
    modifier: Modifier = Modifier
) {
    when(this) {
        is TopBarType.TopBarTextButton -> {
            TopBarTextButton(
                modifier = modifier,
                text = stringResource(id = this.stringId),
                onClick = this.onClick
            )
        }
        is TopBarType.TopBarIconButton -> {
            DayPetIcon(
                modifier = modifier,
                icon = IconDefault(
                    painter = painterResource(id = this.iconId),
                    size = 32.dp,
                    alignment = this.iconAlignment
                ),
                onClick = this.onClick
            )
        }
        is TopBarType.TopBarTitle -> {
            Text(
                modifier = modifier,
                text = stringResource(id = this.stringId),
                style = Heading4
            )
        }
        is TopBarType.TopBarTitleString -> {
            Text(
                modifier = modifier,
                text = this.string,
                style = Heading4
            )
        }
        null -> { }
    }
}