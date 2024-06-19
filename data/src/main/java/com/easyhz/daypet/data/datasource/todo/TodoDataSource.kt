package com.easyhz.daypet.data.datasource.todo

import com.easyhz.daypet.data.model.request.todo.TodoRequest
import com.easyhz.daypet.data.model.response.todo.TodoResponse

interface TodoDataSource {

    suspend fun fetchTodosOnDate(data: TodoRequest): Result<List<TodoResponse>>
}