package com.easyhz.daypet.memory_detail

import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.domain.usecase.comment.FetchMemoryCommentListUseCase
import com.easyhz.daypet.domain.usecase.memory.FetchMemoryUseCase
import com.easyhz.daypet.memory_detail.contract.DetailIntent
import com.easyhz.daypet.memory_detail.contract.DetailSideEffect
import com.easyhz.daypet.memory_detail.contract.DetailState
import com.easyhz.daypet.memory_detail.contract.toMemoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoryDetailViewModel @Inject constructor(
    private val fetchMemoryUseCase: FetchMemoryUseCase,
    private val fetchMemoryCommentListUseCase: FetchMemoryCommentListUseCase,
):BaseViewModel<DetailState, DetailIntent, DetailSideEffect>(
    initialState = DetailState.init()
) {
    override fun handleIntent(intent: DetailIntent) {
        when(intent) {
            is DetailIntent.InitScreen -> { initScreen(intent.id) }
            is DetailIntent.ClickBackButton -> { onClickBackButton() }
            is DetailIntent.ClickComment -> { onClickComment() }
        }
    }

    private fun initScreen(id: String) = viewModelScope.launch {
        launch {
            fetchMemoryUseCase.invoke(id).onSuccess {
                reduce { copy(memoryState = it.toMemoryState()) }
            }.onFailure {
                /*FIXME 예외 처리 */
                onClickBackButton()
            }
        }
        launch {
            fetchCommentList(id)
        }
    }

    private fun fetchCommentList(id: String) = viewModelScope.launch {
        fetchMemoryCommentListUseCase.invoke(id).onSuccess {
            reduce { copy(commentList = it) }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { DetailSideEffect.NavigateToUp }
    }

    private fun onClickComment() {
        val memory = currentState.memoryState
        postSideEffect { DetailSideEffect.NavigateToComment(memory.documentId, memory.title, memory.thumbnailUrl) }
    }
}