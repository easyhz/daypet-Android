package com.easyhz.daypet.home.view.upload

import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.home.contract.upload.UploadTodoIntent
import com.easyhz.daypet.home.contract.upload.UploadTodoSideEffect
import com.easyhz.daypet.home.contract.upload.UploadTodoState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UploadTodoViewModel @Inject constructor(

): BaseViewModel<UploadTodoState, UploadTodoIntent, UploadTodoSideEffect>(
    initialState = UploadTodoState.init()
) {
    override fun handleIntent(intent: UploadTodoIntent) {
        when(intent) {
            is UploadTodoIntent.ChangeDate -> { reduce { copy(selectedDate = intent.date) } }
            is UploadTodoIntent.ChangeColor -> { reduce { copy(selectedColor = intent.color) } }
            is UploadTodoIntent.ChangeText -> { reduce { copy(todoText = intent.text) } }
            is UploadTodoIntent.ChangeDateExpanded -> { reduce { copy(isDateExpanded = intent.expanded) } }
        }
    }
}