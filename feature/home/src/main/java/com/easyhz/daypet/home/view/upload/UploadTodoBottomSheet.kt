package com.easyhz.daypet.home.view.upload

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.bottomSheet.BottomSheet
import com.easyhz.daypet.design_system.component.picker.DatePickerButton
import com.easyhz.daypet.design_system.component.textField.BaseTextField
import com.easyhz.daypet.design_system.component.textField.TextFieldType
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubTitle
import com.easyhz.daypet.design_system.util.color.TodoColor
import com.easyhz.daypet.home.component.TodoColorView
import com.easyhz.daypet.home.contract.upload.UploadTodoIntent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UploadTodoBottomSheet(
    viewModel: UploadTodoViewModel = hiltViewModel(),
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    onDismissRequest: () -> Unit,
    onClickAdd: (selectedDate: LocalDate, selectedColor: TodoColor, todoText: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = uiState.isDateExpanded) {
        if (uiState.isDateExpanded) {
            focusManager.clearFocus()
        } else {
            focusRequester.requestFocus()
        }
    }
    DisposableEffect(Unit) {
        scope.launch {
            delay(300)
            focusRequester.requestFocus()
        }
        onDispose {
            focusManager.clearFocus()
        }
    }
    BottomSheet(sheetState = sheetState, onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DatePickerButton(
                modifier = Modifier.padding(horizontal = 4.dp),
                date = uiState.selectedDate,
                onChangeExpanded = { viewModel.postIntent(UploadTodoIntent.ChangeDateExpanded(it)) },
            ) { viewModel.postIntent(UploadTodoIntent.ChangeDate(it)) }
            TodoColorView(selectedColor = uiState.selectedColor) {
                viewModel.postIntent(UploadTodoIntent.ChangeColor(it))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseTextField(
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester),
                    value = uiState.todoText,
                    onValueChange = { viewModel.postIntent(UploadTodoIntent.ChangeText(it)) },
                    title = null,
                    placeholder = stringResource(id = R.string.todo_upload_placeholder),
                    textFieldType = TextFieldType.NO_LINE,
                    singleLine = true,
                    isFilled = false,
                )
                Box(modifier = Modifier.heightIn(min = 32.dp).widthIn(min = 32.dp).noRippleClickable { onClickAdd(uiState.selectedDate, uiState.selectedColor, uiState.todoText) }, contentAlignment = Alignment.CenterEnd) {
                    Text(
                        text = stringResource(id = R.string.todo_upload_button),
                        style = SubTitle,
                        color = Primary,
                    )
                }
            }
            AnimatedVisibility(visible = uiState.isDateExpanded) {
                Box(modifier = Modifier.height(300.dp))
            }
        }
    }
}