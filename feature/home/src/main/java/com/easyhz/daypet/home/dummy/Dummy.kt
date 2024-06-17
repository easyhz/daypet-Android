package com.easyhz.daypet.home.dummy

import com.easyhz.daypet.domain.model.memory.Memory
import com.easyhz.daypet.domain.model.todo.Todo

val MEMORY_DUMMY = listOf(
    Memory(title = "산책 갔엉", "https://picsum.photos/id/237/200/300", "10:07")
)

val TODO_DUMMY = listOf(
    Todo(title = "목욕 하기", isDone = false),
    Todo(title = "산책하기", isDone = true),
)