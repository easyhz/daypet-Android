package com.easyhz.daypet.domain.usecase.todo

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.model.todo.Todo
import com.easyhz.daypet.domain.param.todo.TodoParam
import com.easyhz.daypet.domain.repository.todo.TodoRepository
import javax.inject.Inject

class FetchTodosUseCase @Inject constructor(
    private val todoRepository: TodoRepository
): BaseUseCase<TodoParam, List<Todo>>() {
    override suspend fun invoke(param: TodoParam): Result<List<Todo>> =
        todoRepository.fetchTodosOnDate(param)
}