package com.easyhz.daypet.memory_detail.screen.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.common.extension.collectInLaunchedEffectWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.DayPetScaffold
import com.easyhz.daypet.design_system.component.topbar.TopBar
import com.easyhz.daypet.design_system.extension.borderTop
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.Body1
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.SubTextColor
import com.easyhz.daypet.design_system.util.topbar.TopBarType
import com.easyhz.daypet.memory_detail.component.CommentBottomBar
import com.easyhz.daypet.memory_detail.component.CommentItem
import com.easyhz.daypet.memory_detail.contract.comment.CommentIntent
import com.easyhz.daypet.memory_detail.contract.comment.CommentSideEffect

@Composable
fun CommentScreen(
    modifier: Modifier = Modifier,
    viewModel: CommentViewModel = hiltViewModel(),
    memoryId: String,
    memoryTitle: String,
    thumbnailUrl: String,
    navigateToUp: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    LaunchedEffect(key1 = Unit) {
        viewModel.postIntent(CommentIntent.InitScreen(memoryId, memoryTitle, thumbnailUrl))
    }
    DayPetScaffold(
        modifier = Modifier.noRippleClickable { focusManager.clearFocus() },
        topBar = {
            TopBar(
                left = TopBarType.TopBarIconButton(
                    iconId = R.drawable.ic_keyboard_arrow_left,
                    iconAlignment = Alignment.CenterStart,
                    onClick = {
                        viewModel.postIntent(CommentIntent.ClickBackButton)
                    }
                ),
                title = TopBarType.TopBarTitleString(
                    string = stringResource(
                        id = R.string.comment_title,
                        (uiState.comment.size.takeIf { it > 0 } ?: "").toString())
                ),
                right = TopBarType.TopBarIconButton(
                    iconId = R.drawable.ic_more_horiz,
                    iconAlignment = Alignment.CenterEnd,
                    onClick = { }
                )
            )
        },
        bottomBar = {
            CommentBottomBar(
                modifier = Modifier
                    .borderTop(color = ButtonShapeColor, width = 1.dp)
                    .screenHorizonPadding()
                    .imePadding()
                    .padding(vertical = 8.dp),
                text = uiState.commentString,
                onValueChange = { viewModel.postIntent(CommentIntent.ChangeValueTextField(it)) }
            ) {
                viewModel.postIntent(CommentIntent.ClickSendButton)
            }
        }
    ) { paddingValues ->
        if (uiState.comment.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = R.string.comment_empty),
                    textAlign = TextAlign.Center,
                    style = Body1,
                    color = SubTextColor
                )
            }
        }

        LazyColumn(
            modifier = modifier
                .screenHorizonPadding()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }
            items(uiState.comment) {
                CommentItem(
                    comment = it
                ) {

                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }

    viewModel.sideEffect.collectInLaunchedEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is CommentSideEffect.NavigateToUp -> {
                navigateToUp()
            }
            is CommentSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }
            is CommentSideEffect.HideKeyboard -> {
                focusManager.clearFocus()
            }
        }
    }

}