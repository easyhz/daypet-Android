package com.easyhz.daypet.upload_memory.view.upload

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.bottomSheet.BottomSheet
import com.easyhz.daypet.design_system.component.picker.DatePickerButton
import com.easyhz.daypet.design_system.component.picker.TimePickerButton
import com.easyhz.daypet.design_system.component.textField.BaseTextField
import com.easyhz.daypet.design_system.component.textField.ContentTextField
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.upload_memory.UploadMemoryViewModel
import com.easyhz.daypet.upload_memory.contract.UploadIntent

/**
 * TODO: 화면 밖 터치하면 키보드 내리기
 * TODO: 멤버 삭제 추가 로직 -> Bottom Sheet 처리 어디서?
 * TODO: 멤버 파라미터 변경
 * TODO: content FOCUS 될떄 스크롤 처리
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UploadView(
    modifier: Modifier = Modifier,
    viewModel: UploadMemoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isShowMemberBottomSheet) {
        BottomSheet(onDismissRequest = { viewModel.postIntent(UploadIntent.HideMemberBottomSheet) }) {
            MemberSelectView(
                pets = uiState.pets,
                members = uiState.users
            )
        }
    }
    Column(
        modifier = modifier.navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        UploadTitle(
            modifier = Modifier
                .padding(top = 8.dp)
                .screenHorizonPadding(),
            text = uiState.title,
            onChangeFocus = { viewModel.postIntent(UploadIntent.ChangeTitleFocus(it)) }
        ) {
            viewModel.postIntent(UploadIntent.ChangeTitleText(it))
        }
        Row(
            modifier = Modifier.screenHorizonPadding(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DatePickerButton(uiState.date) { viewModel.postIntent(UploadIntent.ChangeDate(it)) }
            TimePickerButton(uiState.time) { viewModel.postIntent(UploadIntent.ChangeTime(it)) }
        }
        UploadImageView(
            imageUrls = uiState.selectedImages,
            onClickNoImage = { viewModel.postIntent(UploadIntent.ClickToGallery)}
        ) {
            viewModel.postIntent(UploadIntent.DeleteImage(it))
        }
        UploadMemberView(
            modifier = Modifier.padding(top = 4.dp),
            members = uiState.selectedMembers,
            onAddClick = { viewModel.postIntent(UploadIntent.ShowMemberBottomSheet)}
        )
        UploadContent(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 12.dp).screenHorizonPadding(),
            text = uiState.content,
            onChangeFocus = { viewModel.postIntent(UploadIntent.ChangeContentFocus(it)) }
        ) {
            viewModel.postIntent(UploadIntent.ChangeContentText(it))
        }
    }
}

@Composable
private fun UploadTitle(
    modifier: Modifier = Modifier,
    text: String,
    onChangeFocus: (Boolean) -> Unit,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier.onFocusChanged { onChangeFocus(it.isFocused) },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        BaseTextField(
            value = text,
            onValueChange = onValueChange,
            title = stringResource(id = R.string.upload_title),
            placeholder = stringResource(id = R.string.upload_title_placeholder),
            singleLine = true,
            isFilled = false
        )
    }
}

@Composable
private fun UploadContent(
    modifier: Modifier,
    text: String,
    onChangeFocus: (Boolean) -> Unit,
    onValueChange: (String) -> Unit
) {
    ContentTextField(
        modifier = modifier.onFocusChanged { onChangeFocus(it.isFocused) },
        value = text,
        onValueChange = onValueChange,
        title = stringResource(id = R.string.upload_content),
        placeholder = stringResource(id = R.string.upload_content_placeholder),
        singleLine = false,
        isFilled = false,
        minLines = 3
    )
}