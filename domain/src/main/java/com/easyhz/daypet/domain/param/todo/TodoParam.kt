package com.easyhz.daypet.domain.param.todo

import java.time.LocalDate

data class TodoParam(
    val groupId: String,
    val startDate: LocalDate,
    val endDate: LocalDate
)
