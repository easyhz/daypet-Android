package com.easyhz.daypet.domain.param.memory

import java.time.LocalDate

data class MemoryParam(
    val groupId: String,
    val startDate: LocalDate,
    val endDate: LocalDate
)
