package com.easyhz.daypet.domain.usecase.todo

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.param.todo.CreateTodoParam
import com.easyhz.daypet.domain.repository.todo.TodoRepository
import javax.inject.Inject

class CreateTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
): BaseUseCase<CreateTodoParam, Unit>() {
    override suspend fun invoke(param: CreateTodoParam): Result<Unit> {
        return todoRepository.createTodo(param)
    }
}