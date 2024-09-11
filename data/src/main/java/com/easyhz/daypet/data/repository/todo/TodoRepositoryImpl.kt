package com.easyhz.daypet.data.repository.todo

import com.easyhz.daypet.data.datasource.todo.TodoDataSource
import com.easyhz.daypet.data.mapper.todo.toModel
import com.easyhz.daypet.data.mapper.todo.toRequest
import com.easyhz.daypet.domain.model.todo.Todo
import com.easyhz.daypet.domain.param.todo.CreateTodoParam
import com.easyhz.daypet.domain.param.todo.TodoParam
import com.easyhz.daypet.domain.repository.todo.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDataSource: TodoDataSource
): TodoRepository {
    override suspend fun fetchTodosOnDate(param: TodoParam): Result<List<Todo>> {
        return todoDataSource.fetchTodosOnDate(param.toRequest()).map { list ->
            list.map { res -> res.toModel() }
        }
    }

    override suspend fun createTodo(param: CreateTodoParam): Result<Unit> =
        todoDataSource.createTodo(param.toRequest())
}