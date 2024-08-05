package com.easyhz.daypet.memory_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.common.extension.collectInLaunchedEffectWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.topbar.TopBar
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.Body2
import com.easyhz.daypet.design_system.theme.Body5
import com.easyhz.daypet.design_system.theme.Heading3
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.TextColor
import com.easyhz.daypet.design_system.util.topbar.TopBarType
import com.easyhz.daypet.memory_detail.component.DetailBottomBar
import com.easyhz.daypet.memory_detail.component.ImageView
import com.easyhz.daypet.memory_detail.component.MemberView
import com.easyhz.daypet.memory_detail.contract.DetailIntent
import com.easyhz.daypet.memory_detail.contract.DetailSideEffect

@Composable
fun MemoryDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: MemoryDetailViewModel = hiltViewModel(),
    id: String,
    title: String,
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState { uiState.memoryState.imageUrl.size }
    LaunchedEffect(Unit) {
        viewModel.postIntent(DetailIntent.InitScreen(id, title))
    }
    DayPetScaffold(
        topBar = {
            TopBar(
                left = TopBarType.TopBarIconButton(
                    iconId = R.drawable.ic_keyboard_arrow_left,
                    iconAlignment = Alignment.CenterStart,
                    onClick = { viewModel.postIntent(DetailIntent.ClickBackButton) }
                ),
                title = null,
                right = TopBarType.TopBarIconButton(
                    iconId = R.drawable.ic_more_horiz,
                    iconAlignment = Alignment.CenterEnd,
                    onClick = { }
                )
            )
        },
        bottomBar = {
            DetailBottomBar(
                commentCount = 3
            ) {
                viewModel.postIntent(DetailIntent.ClickComment)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ImageView(
                pagerState = pagerState,
                itemCount = uiState.memoryState.imageUrl.size,
                imageList = uiState.memoryState.imageUrl
            )
            Column(
                modifier = Modifier.screenHorizonPadding(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = uiState.memoryState.date, style = Body2, color = Primary)
                Column {
                    Text(text = uiState.memoryState.title, style = Heading3, color = TextColor)
                    MemberView(
                        modifier = Modifier.height(44.dp),
                        members = uiState.memoryState.petsId + uiState.memoryState.membersId
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = uiState.memoryState.content,
                        style = Body5
                    )
                }
            }
        }
    }

    viewModel.sideEffect.collectInLaunchedEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is DetailSideEffect.NavigateToUp -> { navigateToUp() }
        }
    }
}