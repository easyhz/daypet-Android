package com.easyhz.daypet.domain.param.todo

import java.time.LocalDate

data class TodoParam(
    val groupId: String,
    val startDate: LocalDate,
    val endDate: LocalDate
)

data class CreateTodoParam(
    val groupId: String,
    val uploaderId: String,
    val title: String,
    val todoColor: String,
    val todoDate: LocalDate
)