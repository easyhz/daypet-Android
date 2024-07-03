package com.easyhz.daypet.sign.view.group

import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.sign.contract.group.GroupIntent
import com.easyhz.daypet.sign.contract.group.GroupSideEffect
import com.easyhz.daypet.sign.contract.group.GroupState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(

): BaseViewModel<GroupState, GroupIntent, GroupSideEffect>(
    initialState = GroupState.init()
) {
    override fun handleIntent(intent: GroupIntent) {
        when(intent) {
            is GroupIntent.ChangeNameText -> { /* TODO */}
            is GroupIntent.ClickCreateGroup -> { /* TODO */}
            is GroupIntent.ClickEnterGroup -> { /* TODO */}
        }
    }
}