package com.easyhz.daypet.domain.param.todo

import java.time.LocalDate

data class TodoParam(
    val startDate: LocalDate,
    val endDate: LocalDate
)
