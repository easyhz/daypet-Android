package com.easyhz.daypet.memory_detail.screen.comment

import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.common.error.getMessageStringRes
import com.easyhz.daypet.domain.manager.UserManager
import com.easyhz.daypet.domain.model.comment.Comment
import com.easyhz.daypet.domain.usecase.comment.CreateMemoryCommentUseCase
import com.easyhz.daypet.domain.usecase.comment.FetchMemoryCommentListUseCase
import com.easyhz.daypet.memory_detail.contract.comment.CommentIntent
import com.easyhz.daypet.memory_detail.contract.comment.CommentSideEffect
import com.easyhz.daypet.memory_detail.contract.comment.CommentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val fetchMemoryCommentListUseCase: FetchMemoryCommentListUseCase,
    private val createMemoryCommentUseCase: CreateMemoryCommentUseCase,
) : BaseViewModel<CommentState, CommentIntent, CommentSideEffect>(
    initialState = CommentState.init()
) {
    override fun handleIntent(intent: CommentIntent) {
        when (intent) {
            is CommentIntent.ClickBackButton -> {
                onClickBackButton()
            }
            is CommentIntent.InitScreen -> { initScreen(intent.memoryId, intent.memoryTitle, intent.thumbnailUrl) }
            is CommentIntent.ChangeValueTextField -> { onChangeValueTextField(intent.text) }
            is CommentIntent.ClickSendButton -> { onClickSendButton() }
        }
    }
    private fun initScreen(id: String, title: String, url: String) {
        reduce { copy(memoryId = id, memoryTitle = title, thumbnailUrl = url) }
        fetchMemoryCommentList(id)
    }
    private fun onClickBackButton() {
        postSideEffect { CommentSideEffect.NavigateToUp }
    }

    private fun fetchMemoryCommentList(id: String) = viewModelScope.launch {
        reduce { copy(memoryId = id) }
        fetchMemoryCommentListUseCase.invoke(id)
            .onSuccess {
                reduce { copy(comment = it) }
            }.onFailure {
                postSideEffect { CommentSideEffect.ShowSnackBar(it.getMessageStringRes()) }
            }
    }

    private fun onChangeValueTextField(text: String) {
        reduce { copy(commentString = text) }
    }

    private fun onClickSendButton() = viewModelScope.launch {
        val user = UserManager.groupInfo?.groupUsers?.find { it.userId == UserManager.userId }
        val param = user?.let {
            Comment(
                commentId = "",
                content = currentState.commentString,
                creationTime = LocalDateTime.now().toString(),
                groupId = it.groupId,
                memoryId = currentState.memoryId,
                memoryTitle = currentState.memoryTitle,
                thumbnailUrl = currentState.thumbnailUrl,
                profileImageUrl = "",
                uploaderId = it.userId,
                uploaderName = it.name,
                isOwner = true
            )
        } ?: return@launch
        createMemoryCommentUseCase.invoke(param).onSuccess {
            reduce { copy(commentString = "") }
            fetchMemoryCommentList(currentState.memoryId)
        }.onFailure {
            postSideEffect { CommentSideEffect.ShowSnackBar(it.getMessageStringRes()) }
        }.also {
            postSideEffect { CommentSideEffect.HideKeyboard }
        }
    }
}