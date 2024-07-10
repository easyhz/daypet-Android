package com.easyhz.daypet.data.mapper.todo

import com.easyhz.daypet.common.extension.toTimeStamp
import com.easyhz.daypet.data.model.request.todo.TodoRequest
import com.easyhz.daypet.data.model.response.todo.TodoResponse
import com.easyhz.daypet.domain.model.todo.Todo
import com.easyhz.daypet.domain.param.todo.TodoParam

fun TodoResponse.toModel(): Todo = Todo(
    title = this.title,
    isDone = this.isDone,
    todoColor = this.todoColor
)

fun TodoParam.toRequest(): TodoRequest = TodoRequest(
    startDate = this.startDate.toTimeStamp(),
    endDate = this.startDate.toTimeStamp(1),
    groupId = this.groupId
)