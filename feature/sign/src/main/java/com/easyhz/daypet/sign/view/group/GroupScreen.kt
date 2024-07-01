package com.easyhz.daypet.sign.view.group

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.topbar.TopBar
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.util.topbar.TopBarType

@Composable
fun GroupScreen(
    navigateToBack: () -> Unit
) {
    DayPetScaffold(
        topBar = {
            TopBar(
                left = null,
                title = TopBarType.TopBarTitle(
                    stringId = R.string.title_group
                ),
                right = null
            )
        }
    ) {
        Box(modifier = Modifier
            .padding(it)
            .screenHorizonPadding()) {
            Text(text = "그룹 설정 어쩌구")
        }
    }
}