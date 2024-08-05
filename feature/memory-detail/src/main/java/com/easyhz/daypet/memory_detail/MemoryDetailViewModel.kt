package com.easyhz.daypet.memory_detail

import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
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
    private val fetchMemoryUseCase: FetchMemoryUseCase
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
        fetchMemoryUseCase.invoke(id).onSuccess {
            reduce { copy(memoryState = it.toMemoryState()) }
        }.onFailure {
            /*FIXME 예외 처리 */
            onClickBackButton()
        }
    }

    private fun onClickBackButton() {
        postSideEffect { DetailSideEffect.NavigateToUp }
    }

    private fun onClickComment() {

    }
}